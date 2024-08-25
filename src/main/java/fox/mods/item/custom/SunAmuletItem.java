package fox.mods.item.custom;

import fox.mods.item.ModItems;
import fox.mods.utils.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SunAmuletItem extends Item {

    private static final int COOLDOWN_TICKS = 6000;

    public SunAmuletItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient()) {
            if (player instanceof ServerPlayerEntity serverPlayer) {

                ServerWorld serverWorld = (ServerWorld) world;

                long timeOfDay = serverWorld.getTimeOfDay() % 24000;

                if (timeOfDay < 12000) {
                    serverWorld.setTimeOfDay(13000);
                    player.sendMessage(Text.literal("The day turns to night..."), true);
                } else {
                    serverWorld.setTimeOfDay(1000);
                    player.sendMessage(Text.literal("The night turns to day..."), true);
                }

                serverWorld.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                serverPlayer.getItemCooldownManager().set(this, COOLDOWN_TICKS);


                return TypedActionResult.success(itemStack, true);
            }
        }
        return TypedActionResult.success(itemStack, true);
    }
}