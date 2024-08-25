package fox.mods;

import fox.mods.configuration.MinersGraceConfig;
import fox.mods.configuration.MinersGraceFileConfiguration;
import fox.mods.enchantments.AutoSmeltEnchantment;
import fox.mods.enchantments.VeinMinerEnchantment;
import fox.mods.item.ModItems;
import fox.mods.item.ModItemsGroup;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fox.mods.configuration.MinersGraceFileConfiguration;

import java.util.HashSet;
import java.util.Set;

public class MinersGrace implements ModInitializer {
	public static final String MOD_ID = "miners-grace";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Enchantment AUTO_SMELT = new AutoSmeltEnchantment();
	public static Enchantment VEIN_MINER = new VeinMinerEnchantment();

	public static final MinersGraceConfig CONFIG = MinersGraceConfig.createAndLoad();

	private void spawnSmeltedItem(World world, BlockPos pos, ItemStack itemStack) {
		ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), itemStack);
		itemEntity.setVelocity(Vec3d.ZERO);
		world.spawnEntity(itemEntity);
	}

	//public static void checkForBlockInGrid(World world, BlockPos centerPos, Block targetBlock) {
		//int gridRadius = 5;

		//for (int x = -gridRadius; x <= gridRadius; x++) {
			//for (int y = -gridRadius; y <= gridRadius; y++) {
				//for (int z = -gridRadius; z <= gridRadius; z++) {
					//BlockPos pos = centerPos.add(x, 0, z);

					//Block block = world.getBlockState(pos).getBlock();

					//if (block == targetBlock) {
						//if (!world.isClient()) {
						//	world.breakBlock(pos, true, null);
						//}
					//}
				//}
			//}
		//}
	//}

	@Override
	public void onInitialize() {
		ModItemsGroup.registerItemGroup();

		ModItems.registerModItems();

		Registry.register(Registries.ENCHANTMENT, new Identifier("miners-grace", "auto_smelt"), AUTO_SMELT);
		Registry.register(Registries.ENCHANTMENT, new Identifier("miners-grace", "vein_miner"), VEIN_MINER);


		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			if (!world.isClient) {
				if (player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.XP_EXTRACTOR)) {
					ExperienceOrbEntity xpOrb = new ExperienceOrbEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, CONFIG.ExperienceExtractorExperienceDrop());
					world.spawnEntity(xpOrb);
				}
			}
			return true;
		});


		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			if (!world.isClient) {
				ItemStack tool = player.getMainHandStack();
				int autoSmeltLevel = EnchantmentHelper.getLevel(AUTO_SMELT, tool);

				if (autoSmeltLevel > 0) {
					double chance = switch (autoSmeltLevel) {
						case 1 -> CONFIG.AutoSmeltLevelOneSmeltingChance();
						case 2 -> CONFIG.AutoSmeltLevelTwoSmeltingChance();
						case 3 -> CONFIG.AutoSmeltLevelThreeSmeltingChance();
						default -> 0.0;
					};

					if (Math.random() < chance) {
						Block block = state.getBlock();

						if (block == Blocks.IRON_ORE) {
							spawnSmeltedItem(world, pos, new ItemStack(Items.IRON_INGOT));
							world.breakBlock(pos, false, player);
							return false;
						} else if (block == Blocks.COPPER_ORE) {
							spawnSmeltedItem(world, pos, new ItemStack(Items.COPPER_INGOT));
							world.breakBlock(pos, false, player);
							return false;
						} else if (block == Blocks.GOLD_ORE) {
							spawnSmeltedItem(world, pos, new ItemStack(Items.GOLD_INGOT));
							world.breakBlock(pos, false, player);
							return false;
						} else if (block == Blocks.ANCIENT_DEBRIS) {
							spawnSmeltedItem(world, pos, new ItemStack(Items.NETHERITE_SCRAP));
							world.breakBlock(pos, false, player);
							return false;
						}
					}
				}
			}
			return true;
		});

		// PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			//if (!world.isClient) {
				//ItemStack tool = player.getMainHandStack();
				//int veinMinerLevel = EnchantmentHelper.getLevel(VEIN_MINER, tool);

				//if (veinMinerLevel > 0) {
					//Block minedBlock = state.getBlock();
					//BlockPos playerPos = player.getBlockPos();
					//if (minedBlock == Blocks.IRON_ORE || minedBlock == Blocks.COPPER_ORE || minedBlock == Blocks.GOLD_ORE || minedBlock == Blocks.COAL_ORE || minedBlock == Blocks.EMERALD_ORE || minedBlock == Blocks.DIAMOND_ORE || minedBlock == Blocks.LAPIS_ORE || minedBlock == Blocks.REDSTONE_ORE || minedBlock == Blocks.DEEPSLATE_IRON_ORE || minedBlock == Blocks.DEEPSLATE_COAL_ORE || minedBlock == Blocks.DEEPSLATE_COPPER_ORE || minedBlock == Blocks.DEEPSLATE_GOLD_ORE || minedBlock == Blocks.DEEPSLATE_EMERALD_ORE || minedBlock == Blocks.DEEPSLATE_DIAMOND_ORE || minedBlock == Blocks.DEEPSLATE_LAPIS_ORE || minedBlock == Blocks.DEEPSLATE_REDSTONE_ORE) {
						//checkForBlockInGrid(player.getWorld(), playerPos, minedBlock);
					//}
					//return false;
				//}
				//return false;
			//}
			//return false;
		//});

		LOGGER.info("Miner's Grace loaded successfully!");
	}
}
