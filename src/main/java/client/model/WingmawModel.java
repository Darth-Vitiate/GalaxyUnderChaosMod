package client.model;


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import server.galaxyunderchaos.entity.WingmawEntity;
import server.galaxyunderchaos.galaxyunderchaos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class WingmawModel<T extends WingmawEntity> extends HierarchicalModel<T> {    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "wingmaw"), "main");
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart root;
	private final ModelPart Leftthigh;
	private final ModelPart Leftcalf;
	private final ModelPart Leftupperfoot;
	private final ModelPart Leftfoot;
	private final ModelPart Rightthigh;
	private final ModelPart rightcalf;
	private final ModelPart Rightupperfoot;
	private final ModelPart Rightfoot;
	private final ModelPart Body1;
	private final ModelPart Body2;
	private final ModelPart Body3;
	private final ModelPart Neck;
	private final ModelPart Neck2;
	private final ModelPart Head;
	private final ModelPart LowerJaw1;
	private final ModelPart LowerJaw2;
	private final ModelPart LowerJaw3;
	private final ModelPart LowerTeeth2;
	private final ModelPart LowerTeeth1;
	private final ModelPart LowerTeeth3;
	private final ModelPart UpperJaw1;
	private final ModelPart UpperJaw4;
	private final ModelPart UpperJaw5;
	private final ModelPart UpperJaw6;
	private final ModelPart UpperJaw7;
	private final ModelPart UpperJaw8;
	private final ModelPart Neck3;
	private final ModelPart Neck4;
	private final ModelPart Neck5;
	private final ModelPart Neck6;
	private final ModelPart Tail1;
	private final ModelPart Tail2;
	private final ModelPart Tail3;
	private final ModelPart Tail4;
	private final ModelPart Tail5;
	private final ModelPart Tail6;
	private final ModelPart RightArm1;
	private final ModelPart RightArm2;
	private final ModelPart RightArm3;
	private final ModelPart RightArm4;
	private final ModelPart RightFinger1;
	private final ModelPart RightFinger2;
	private final ModelPart RightFinger3;
	private final ModelPart LeftArm1;
	private final ModelPart LeftArm2;
	private final ModelPart LeftArm3;
	private final ModelPart LeftArm4;
	private final ModelPart LeftFinger1;
	private final ModelPart LeftFinger2;
	private final ModelPart LeftFinger3;

	public WingmawModel(ModelPart root) {
		this.root = root.getChild("root");
		this.Leftthigh = this.root.getChild("Leftthigh");
		this.Leftcalf = this.Leftthigh.getChild("Leftcalf");
		this.Leftupperfoot = this.Leftcalf.getChild("Leftupperfoot");
		this.Leftfoot = this.Leftupperfoot.getChild("Leftfoot");
		this.Rightthigh = this.root.getChild("Rightthigh");
		this.rightcalf = this.Rightthigh.getChild("rightcalf");
		this.Rightupperfoot = this.rightcalf.getChild("Rightupperfoot");
		this.Rightfoot = this.Rightupperfoot.getChild("Rightfoot");
		this.Body1 = this.root.getChild("Body1");
		this.Body2 = this.Body1.getChild("Body2");
		this.Body3 = this.Body2.getChild("Body3");
		this.Neck = this.Body3.getChild("Neck");
		this.Neck2 = this.Neck.getChild("Neck2");
		this.Head = this.Neck2.getChild("Head");
		this.LowerJaw1 = this.Head.getChild("LowerJaw1");
		this.LowerJaw2 = this.LowerJaw1.getChild("LowerJaw2");
		this.LowerJaw3 = this.LowerJaw2.getChild("LowerJaw3");
		this.LowerTeeth2 = this.LowerJaw3.getChild("LowerTeeth2");
		this.LowerTeeth1 = this.LowerJaw2.getChild("LowerTeeth1");
		this.LowerTeeth3 = this.LowerJaw1.getChild("LowerTeeth3");
		this.UpperJaw1 = this.Head.getChild("UpperJaw1");
		this.UpperJaw4 = this.UpperJaw1.getChild("UpperJaw4");
		this.UpperJaw5 = this.UpperJaw4.getChild("UpperJaw5");
		this.UpperJaw6 = this.UpperJaw5.getChild("UpperJaw6");
		this.UpperJaw7 = this.UpperJaw6.getChild("UpperJaw7");
		this.UpperJaw8 = this.UpperJaw7.getChild("UpperJaw8");
		this.Neck3 = this.Neck.getChild("Neck3");
		this.Neck4 = this.Neck3.getChild("Neck4");
		this.Neck5 = this.Neck4.getChild("Neck5");
		this.Neck6 = this.Neck5.getChild("Neck6");
		this.Tail1 = this.Body1.getChild("Tail1");
		this.Tail2 = this.Tail1.getChild("Tail2");
		this.Tail3 = this.Tail2.getChild("Tail3");
		this.Tail4 = this.Tail3.getChild("Tail4");
		this.Tail5 = this.Tail4.getChild("Tail5");
		this.Tail6 = this.Tail5.getChild("Tail6");
		this.RightArm1 = this.root.getChild("RightArm1");
		this.RightArm2 = this.RightArm1.getChild("RightArm2");
		this.RightArm3 = this.RightArm2.getChild("RightArm3");
		this.RightArm4 = this.RightArm3.getChild("RightArm4");
		this.RightFinger1 = this.RightArm3.getChild("RightFinger1");
		this.RightFinger2 = this.RightFinger1.getChild("RightFinger2");
		this.RightFinger3 = this.RightFinger1.getChild("RightFinger3");
		this.LeftArm1 = this.root.getChild("LeftArm1");
		this.LeftArm2 = this.LeftArm1.getChild("LeftArm2");
		this.LeftArm3 = this.LeftArm2.getChild("LeftArm3");
		this.LeftArm4 = this.LeftArm3.getChild("LeftArm4");
		this.LeftFinger1 = this.LeftArm3.getChild("LeftFinger1");
		this.LeftFinger2 = this.LeftFinger1.getChild("LeftFinger2");
		this.LeftFinger3 = this.LeftFinger1.getChild("LeftFinger3");
	}

	public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Leftthigh = root.addOrReplaceChild("Leftthigh", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 10.0F, 2.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition Leftcalf = Leftthigh.addOrReplaceChild("Leftcalf", CubeListBuilder.create().texOffs(90, 0).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, 2.5F, 1.2392F, 0.0F, 0.0F));

		PartDefinition Leftupperfoot = Leftcalf.addOrReplaceChild("Leftupperfoot", CubeListBuilder.create().texOffs(77, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.3F, 0.9F, -0.9948F, 0.0F, 0.0F));

		PartDefinition Leftfoot = Leftupperfoot.addOrReplaceChild("Leftfoot", CubeListBuilder.create().texOffs(52, 38).addBox(-1.47F, 0.0F, -7.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 1.0F, 0.3665F, 0.0F, 0.0F));

		PartDefinition Rightthigh = root.addOrReplaceChild("Rightthigh", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 10.0F, 2.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition rightcalf = Rightthigh.addOrReplaceChild("rightcalf", CubeListBuilder.create().texOffs(90, 0).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, 2.5F, 1.2392F, 0.0F, 0.0F));

		PartDefinition Rightupperfoot = rightcalf.addOrReplaceChild("Rightupperfoot", CubeListBuilder.create().texOffs(77, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.3F, 0.9F, -0.9948F, 0.0F, 0.0F));

		PartDefinition Rightfoot = Rightupperfoot.addOrReplaceChild("Rightfoot", CubeListBuilder.create().texOffs(52, 38).addBox(-1.5F, 0.0F, -7.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 1.0F, 0.3665F, 0.0F, 0.0F));

		PartDefinition Body1 = root.addOrReplaceChild("Body1", CubeListBuilder.create().texOffs(50, 62).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.67F, 3.93F, -0.4712F, 0.0F, 0.0F));

		PartDefinition Body2 = Body1.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(50, 82).addBox(-4.0F, -0.5F, -9.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.5F));

		PartDefinition Body3 = Body2.addOrReplaceChild("Body3", CubeListBuilder.create().texOffs(50, 101).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -6.3F, 0.182F, 0.0F, 0.0F));

		PartDefinition Neck = Body3.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(50, 116).addBox(-2.0F, -3.5F, -5.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition Neck2 = Neck.addOrReplaceChild("Neck2", CubeListBuilder.create().texOffs(74, 115).addBox(-2.0F, -6.0F, -5.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -3.2F, 0.1367F, 0.0F, 0.0F));

		PartDefinition Head = Neck2.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(1, 59).addBox(-3.0F, -1.5F, -5.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.8F, -4.1F, 0.3187F, 0.0F, 0.0F));

		PartDefinition LowerJaw1 = Head.addOrReplaceChild("LowerJaw1", CubeListBuilder.create().texOffs(2, 118).addBox(-2.0F, 0.0F, -5.1F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.3F, -1.7F, 0.0698F, 0.0F, 0.0F));

		PartDefinition LowerJaw2 = LowerJaw1.addOrReplaceChild("LowerJaw2", CubeListBuilder.create().texOffs(25, 118).addBox(-1.5F, -2.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -5.0F, -0.4554F, 0.0F, 0.0F));

		PartDefinition LowerJaw3 = LowerJaw2.addOrReplaceChild("LowerJaw3", CubeListBuilder.create().texOffs(18, 116).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -4.0F, 0.182F, 0.0F, 0.0F));

		PartDefinition LowerTeeth2 = LowerJaw3.addOrReplaceChild("LowerTeeth2", CubeListBuilder.create().texOffs(88, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition LowerTeeth1 = LowerJaw2.addOrReplaceChild("LowerTeeth1", CubeListBuilder.create().texOffs(75, 13).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition LowerTeeth3 = LowerJaw1.addOrReplaceChild("LowerTeeth3", CubeListBuilder.create().texOffs(76, 19).addBox(-2.0F, -1.0F, -6.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.8F));

		PartDefinition UpperJaw1 = Head.addOrReplaceChild("UpperJaw1", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 0.0F, -5.2F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.3F, -2.1F));

		PartDefinition UpperJaw4 = UpperJaw1.addOrReplaceChild("UpperJaw4", CubeListBuilder.create().texOffs(0, 88).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.4F, 1.4F, 0.0456F, 0.0F, 0.0F));

		PartDefinition UpperJaw5 = UpperJaw4.addOrReplaceChild("UpperJaw5", CubeListBuilder.create().texOffs(29, 84).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -4.7F, -0.0456F, 0.0F, 0.0F));

		PartDefinition UpperJaw6 = UpperJaw5.addOrReplaceChild("UpperJaw6", CubeListBuilder.create().texOffs(22, 98).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, 0.6374F, 0.0F, 0.0F));

		PartDefinition UpperJaw7 = UpperJaw6.addOrReplaceChild("UpperJaw7", CubeListBuilder.create().texOffs(30, 93).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, -0.1367F, 0.0F, 0.0F));

		PartDefinition UpperJaw8 = UpperJaw7.addOrReplaceChild("UpperJaw8", CubeListBuilder.create().texOffs(39, 99).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, 0.3225F, 0.0F, 0.0F));

		PartDefinition Neck3 = Neck.addOrReplaceChild("Neck3", CubeListBuilder.create().texOffs(170, 0).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -1.5F, 0.4554F, 0.0F, 0.0F));

		PartDefinition Neck4 = Neck3.addOrReplaceChild("Neck4", CubeListBuilder.create().texOffs(182, 0).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2276F, 0.0F, 0.0F));

		PartDefinition Neck5 = Neck4.addOrReplaceChild("Neck5", CubeListBuilder.create().texOffs(195, 0).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, -0.4554F, 0.0F, 0.0F));

		PartDefinition Neck6 = Neck5.addOrReplaceChild("Neck6", CubeListBuilder.create().texOffs(207, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.182F, 0.0F, 0.0F));

		PartDefinition Tail1 = Body1.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(18, 40).addBox(-2.0F, -2.3F, -1.4F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.9F, 3.8F, -0.0456F, 0.0F, 0.0F));

		PartDefinition Tail2 = Tail1.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(35, 43).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.3F, 3.0F, 0.2276F, 0.0F, 0.0F));

		PartDefinition Tail3 = Tail2.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(57, 49).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 8.0F, 0.1367F, 0.0F, 0.0F));

		PartDefinition Tail4 = Tail3.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(26, 21).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 4.7F, 0.0456F, 0.0F, 0.0F));

		PartDefinition Tail5 = Tail4.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(0, 45).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 11.0F));

		PartDefinition Tail6 = Tail5.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(0, 39).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6F, 4.5F, 0.1367F, 0.0F, 0.0F));

		PartDefinition RightArm1 = root.addOrReplaceChild("RightArm1", CubeListBuilder.create().texOffs(118, 9).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 3.0F, -6.0F, 0.2731F, 0.0F, 0.5236F));

		PartDefinition RightArm2 = RightArm1.addOrReplaceChild("RightArm2", CubeListBuilder.create().texOffs(120, 43).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.9F, 1.0F, -0.9076F, 0.0456F, -0.3665F));

		PartDefinition RightArm3 = RightArm2.addOrReplaceChild("RightArm3", CubeListBuilder.create().texOffs(118, 64).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.3F, 0.0F, 0.6458F, -0.1361F, 0.0F));

		PartDefinition RightArm4 = RightArm3.addOrReplaceChild("RightArm4", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2F, 11.0F, 0.4F, -1.0472F, 0.4887F, 0.1396F));

		PartDefinition RightArm4_r1 = RightArm4.addOrReplaceChild("RightArm4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -24.8F, -11.0F, 1.0F, 30.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, -2.2F, 4.6F, 0.0F, -0.2182F, -0.7854F));

		PartDefinition RightFinger1 = RightArm3.addOrReplaceChild("RightFinger1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 10.7F, -1.0F, 0.0698F, 0.1222F, -0.0873F));

		PartDefinition RightFinger2 = RightFinger1.addOrReplaceChild("RightFinger2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4538F, 0.0F));

		PartDefinition RightFinger3 = RightFinger1.addOrReplaceChild("RightFinger3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4538F, 0.0F));

		PartDefinition LeftArm1 = root.addOrReplaceChild("LeftArm1", CubeListBuilder.create().texOffs(140, 9).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, -6.0F, 0.2731F, 0.0F, -0.5236F));

		PartDefinition LeftArm2 = LeftArm1.addOrReplaceChild("LeftArm2", CubeListBuilder.create().texOffs(140, 43).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.9F, 1.0F, -0.9076F, -0.0456F, 0.3665F));

		PartDefinition LeftArm3 = LeftArm2.addOrReplaceChild("LeftArm3", CubeListBuilder.create().texOffs(136, 64).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.3F, 0.0F, 0.6458F, 0.1361F, 0.0F));

		PartDefinition LeftArm4 = LeftArm3.addOrReplaceChild("LeftArm4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.2F, 11.0F, 0.4F, -1.0472F, -0.4887F, -0.1396F));

		PartDefinition LeftArm4_r1 = LeftArm4.addOrReplaceChild("LeftArm4_r1", CubeListBuilder.create().texOffs(104, 21).addBox(0.2F, -24.8F, -11.0F, 1.0F, 30.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8F, -2.2F, 4.6F, 0.0F, 0.3927F, 0.7854F));

		PartDefinition LeftFinger1 = LeftArm3.addOrReplaceChild("LeftFinger1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 10.7F, -1.0F, 0.0698F, -0.1222F, 0.0873F));

		PartDefinition LeftFinger2 = LeftFinger1.addOrReplaceChild("LeftFinger2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4538F, 0.0F));

		PartDefinition LeftFinger3 = LeftFinger1.addOrReplaceChild("LeftFinger3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4538F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 128);
		}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// Reset pose for all parts (reset to default position before applying new animation)
		this.Body1.getAllParts().forEach(ModelPart::resetPose);

		// Handle walking animations for legs
		float walkSpeed = 0.3F;
		float walkAmount = 0.3F;

		// Animate legs
		this.Leftthigh.xRot = Mth.cos(limbSwing * walkSpeed) * walkAmount * limbSwingAmount;
		this.Rightthigh.xRot = Mth.cos(limbSwing * walkSpeed + 3.14159F) * walkAmount * limbSwingAmount;

		// Animate arms
//		this.LeftArm1.zRot = Mth.cos(limbSwing * walkSpeed) * walkAmount * limbSwingAmount;
//		this.RightArm1.zRot = Mth.cos(limbSwing * walkSpeed + 3.14159F) * walkAmount * limbSwingAmount;
//
//		// Animate tail for walking motion
		this.Tail1.xRot = Mth.cos(limbSwing * walkSpeed) * 0.1F * limbSwingAmount;
		this.Tail2.xRot = Mth.cos(limbSwing * walkSpeed) * 0.1F * limbSwingAmount;
		this.Tail3.xRot = Mth.cos(limbSwing * walkSpeed) * 0.1F * limbSwingAmount;

		// Flying animation logic
		if (entity.isFallFlying()) {
			// Reset leg and arm rotations for flight
			this.Leftthigh.xRot = 0;
			this.Rightthigh.xRot = 0;
			this.LeftArm1.xRot = 0;
			this.RightArm1.xRot = 0;

			// Add flying movement (flapping)
			this.LeftArm1.xRot = Mth.cos(ageInTicks * 0.6F) * 0.6F;
			this.RightArm1.xRot = -Mth.cos(ageInTicks * 0.6F) * 0.6F;

			// Tail swinging for flying motion
			this.Tail1.xRot = Mth.cos(ageInTicks * 0.4F) * 0.3F;
			this.Tail2.xRot = Mth.cos(ageInTicks * 0.4F) * 0.3F;
			this.Tail3.xRot = Mth.cos(ageInTicks * 0.4F) * 0.3F;

			// Optional: Wing flap animations, if you have wings
			if (this.LeftArm1 != null && this.RightArm1 != null) {
				this.LeftArm1.zRot = Mth.cos(ageInTicks * 0.5F) * 1.0F;
				this.LeftArm1.zRot = -Mth.cos(ageInTicks * 0.5F) * 1.0F;
			}
		}

		// Animate head and neck based on the player's view (yaw/pitch)
//		this.Head.xRot = headPitch * 0.01F;
//		this.Head.yRot = netHeadYaw * 0.01F;
//
//		this.Neck.xRot = headPitch * 0.01F;
//		this.Neck2.xRot = headPitch * 0.01F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Body1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftArm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightArm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Leftthigh.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Rightthigh.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return Body1;
	}
}