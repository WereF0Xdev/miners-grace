package fox.mods.datagen;

import fox.mods.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {


        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR_STAGE_1, 1)
                .pattern("HBH")
                .pattern("HBH")
                .pattern("CSC")
                .input('S', Items.DIAMOND)
                .input('C', Items.IRON_INGOT)
                .input('H', Items.AIR)
                .input('B', Items.STICK)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.METAL_DETECTOR_STAGE_1)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.XP_EXTRACTOR, 1)
                .pattern("CBC")
                .pattern("CSC")
                .pattern("CBC")
                .input('S', Items.EXPERIENCE_BOTTLE)
                .input('C', Items.GLASS)
                .input('B', Items.IRON_INGOT)
                .criterion(hasItem(Items.EXPERIENCE_BOTTLE), conditionsFromItem(Items.EXPERIENCE_BOTTLE))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.XP_EXTRACTOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAGNIFIER, 1)
                .pattern("SFS")
                .pattern("BCB")
                .pattern("BBB")
                .input('S', Items.IRON_BLOCK)
                .input('C', Items.FLINT)
                .input('B', Items.REDSTONE)
                .input('F', Items.AIR)
                .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MAGNIFIER)));
    }

}