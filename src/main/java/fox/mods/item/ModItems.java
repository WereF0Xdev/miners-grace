package fox.mods.item;

import fox.mods.MinersGrace;
import fox.mods.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item METAL_DETECTOR_STAGE_1 = registerItem("metal_detector_stage_one",
            new MetalDetectorItem(new FabricItemSettings().maxDamage(32)));
    public static final Item METAL_DETECTOR_STAGE_2 = registerItem("metal_detector_stage_two",
            new MetalDetectorStageTwoItem(new FabricItemSettings().maxDamage(64)));
    public static final Item METAL_DETECTOR_STAGE_3 = registerItem("metal_detector_stage_three",
            new MetalDetectorStageThreeItem(new FabricItemSettings().maxDamage(128)));

    public static final Item XP_EXTRACTOR = registerItem("xp_extractor",
            new XpExtractorItem(new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item MAGNIFIER = registerItem("magnifier",
            new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item COGWHEEL = registerItem("cogwheel",
            new Item(new FabricItemSettings()));

    public static final Item SUN_AMULET = registerItem("sun_amulet",
            new SunAmuletItem(new FabricItemSettings().rarity(Rarity.EPIC).maxDamage(8)));


    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.METAL_DETECTOR_STAGE_1);
        entries.add(ModItems.XP_EXTRACTOR);
        entries.add(ModItems.MAGNIFIER);
        entries.add(ModItems.COGWHEEL);
        entries.add(ModItems.SUN_AMULET);
    }


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MinersGrace.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MinersGrace.LOGGER.info("Registering Mod Items for " + MinersGrace.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}