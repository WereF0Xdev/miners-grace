package fox.mods.mixin;

import fox.mods.MinersGrace;
import fox.mods.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel UseMetalDetectorStageOneModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean lefthanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.METAL_DETECTOR_STAGE_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(MinersGrace.MOD_ID, "metal_detector_stage_one_3d", "inventory"));
        }
        return value;
    }
    public BakedModel UseMetalDetectorModelStageTwo(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean lefthanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.METAL_DETECTOR_STAGE_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(MinersGrace.MOD_ID, "metal_detector_stage_two_3d", "inventory"));
        }
        return value;
    }
    public BakedModel UseMetalDetectorModelStageThree(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean lefthanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.METAL_DETECTOR_STAGE_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(MinersGrace.MOD_ID, "metal_detector_stage_three_3d", "inventory"));
        }
        return value;
    }
}
