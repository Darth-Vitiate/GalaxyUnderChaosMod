package server.galaxyunderchaos.entity;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class WingmawEntity extends Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public WingmawEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FlyingGoal(this));  // Flying behavior
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, true)); // Aggressive melee attack
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0)); // Wanders when no target
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F)); // Watches players
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this)); // Looks around randomly

        // Target selector (Aggressive AI)
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // Retaliates when hit
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Targets players
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D) // Medium health for toughness
                .add(Attributes.MOVEMENT_SPEED, 0.35D) // Fast spider movement
                .add(Attributes.ATTACK_DAMAGE, 2.0D) // Deals 2 damage per hit
                .add(Attributes.ARMOR, 2.0D) // Slight armor to resist hits
                .add(Attributes.FOLLOW_RANGE, 14.0D); // Slightly Aggressive range
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean flag = super.doHurtTarget(target);
        if (flag && target instanceof LivingEntity livingTarget) {
            livingTarget.hurt(this.damageSources().mobAttack(this), 3.0F); // Corrected method
        }
        return flag;
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public static class FlyingGoal extends Goal {
        private final WingmawEntity wingmaw;

        public FlyingGoal(WingmawEntity wingmaw) {
            this.wingmaw = wingmaw;
        }

        @Override
        public boolean canUse() {
            // Example: Entity can fly if it's not in water or on the ground
            return !wingmaw.isInWater() && wingmaw.getTarget() != null;
        }

        @Override
        public void start() {
            wingmaw.getNavigation().setCanFloat(true);
        }

        @Override
        public void stop() {
            // Stop flying behavior
            wingmaw.getNavigation().setCanFloat(false);
        }

        @Override
        public void tick() {
            if (wingmaw.getTarget() != null) {
                wingmaw.getNavigation().moveTo(wingmaw.getTarget(), 1.0);
            }
        }
    }
}