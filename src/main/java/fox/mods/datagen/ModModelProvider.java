package fox.mods.datagen;

import fox.mods.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.XP_EXTRACTOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGNIFIER, Models.GENERATED);
        itemModelGenerator.register(ModItems.COGWHEEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_DETECTOR_STAGE_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_DETECTOR_STAGE_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_DETECTOR_STAGE_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUN_AMULET, Models.GENERATED);
    }
}
