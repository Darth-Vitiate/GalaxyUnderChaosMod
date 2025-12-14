// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
package client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import server.galaxyunderchaos.galaxyunderchaos;

public class BleedingTableModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "bleeding_table"), "main");

    private final ModelPart root;
    private final ModelPart saberForgeMain;
    private final ModelPart saberForgeMain2;
    private final ModelPart saberForgeMain3;
    private final ModelPart part2;
    private final ModelPart part2Child;
    private final ModelPart part3;
    private final ModelPart part3Detail1;
    private final ModelPart part3Detail2;
    private final ModelPart part3Detail3;
    private final ModelPart part3Detail4;
    private final ModelPart part4;

    public BleedingTableModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
        this.saberForgeMain = root.getChild("SaberForgeMAIN");
        this.saberForgeMain2 = this.saberForgeMain.getChild("SaberForgeMAIN2");
        this.saberForgeMain3 = this.saberForgeMain2.getChild("SaberForgeMAIN3");
        this.part2 = root.getChild("part2");
        this.part2Child = this.part2.getChild("part2_child");
        this.part3 = root.getChild("part3");
        this.part3Detail1 = this.part3.getChild("part3_detail_1");
        this.part3Detail2 = this.part3.getChild("part3_detail_2");
        this.part3Detail3 = this.part3.getChild("part3_detail_3");
        this.part3Detail4 = this.part3.getChild("part3_detail_4");
        this.part4 = root.getChild("part4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition saberForgeMain = partdefinition.addOrReplaceChild("SaberForgeMAIN", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));

        PartDefinition saberForgeMain2 = saberForgeMain.addOrReplaceChild("SaberForgeMAIN2", CubeListBuilder.create().texOffs(0, 49).addBox(-16.0F, 0.0F, 0.0F, 12.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, 0.0F, 5.0F));

        saberForgeMain2.addOrReplaceChild("SaberForgeMAIN3", CubeListBuilder.create().texOffs(46, 49).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-32.0F, 0.0F, 0.0F));

        PartDefinition part2 = partdefinition.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(64, 0).addBox(-13.1F, 0.0F, 0.0F, 13.1F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 8.0F, -8.0F, 0.0F, 0.3519F, 0.0F));

        part2.addOrReplaceChild("part2_child", CubeListBuilder.create().texOffs(64, 21).addBox(-13.1F, 0.0F, 0.0F, 13.1F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, 0.0F, 14.0F, 0.0F, -0.703F, 0.0F));

        PartDefinition part3 = partdefinition.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(0, 32).addBox(-20.0F, 0.0F, 0.0F, 20.0F, 1.0F, 13.5F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 7.6F, -6.4F));

        part3.addOrReplaceChild("part3_detail_1", CubeListBuilder.create().texOffs(35, 49).addBox(-9.6F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 2.7506F, 0.0F));

        part3.addOrReplaceChild("part3_detail_2", CubeListBuilder.create().texOffs(35, 53).addBox(-10.0F, 0.0F, 0.4F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.0F, 0.0F, -6.3F, 0.0F, -2.3721F, 0.0F));

        part3.addOrReplaceChild("part3_detail_3", CubeListBuilder.create().texOffs(54, 42).addBox(-14.1F, 0.0F, -9.25F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.1861F, 0.0F));

        part3.addOrReplaceChild("part3_detail_4", CubeListBuilder.create().texOffs(35, 51).addBox(2.4F, 0.0F, -9.4F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.9291F, 0.0F));

        partdefinition.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(0, 47).addBox(-38.3F, 0.0F, 0.0F, 38.3F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(19.0F, 7.6F, 6.9F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
