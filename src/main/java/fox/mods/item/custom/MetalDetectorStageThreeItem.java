package fox.mods.item.custom;

import fox.mods.utils.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorStageThreeItem extends Item {
    public MetalDetectorStageThreeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;


            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = context.getWorld().getBlockState(positionClicked.down(i));

                if(IsValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.down(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }

            }

            if (!foundBlock) {
                if (!context.getWorld().isClient) {
                    context.getWorld().playSound(
                            null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                            player.getBlockPos(), // The position of where the sound will come from
                            SoundEvents.BLOCK_BEACON_DEACTIVATE, // The sound that will play, in this case, the sound the anvil plays when it lands.
                            SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                            1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                            1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                    );
                    context.getWorld().addParticle(ParticleTypes.ELECTRIC_SPARK, player.getX(), player.getY() + 1, player.getZ(), 0.0, 0.0, 0.0);
                }
            }
            else if (foundBlock && !context.getWorld().isClient) {
                context.getWorld().playSound(
                        null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                        player.getBlockPos(), // The position of where the sound will come from
                        SoundEvents.BLOCK_BEACON_ACTIVATE, // The sound that will play, in this case, the sound the anvil plays when it lands.
                        SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                        1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                        1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                );
            }
        }

        context.getStack().damage(1, context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("§e§lFound " + block.asItem().getName().getString() + " at Y" + "(" + blockPos.getY() +")"), false);
    }

    private boolean IsValuableBlock(BlockState state) {
        return state.isIn(ModTags.Blocks.METAL_DETECTOR_STAGE_THREE_DETECTABLE_BLOCKS);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.miners-grace.metal_detector.tooltip"));
        tooltip.add(Text.translatable("tooltip.miners-grace.metal_detector_stage_three.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
