package fox.mods.item;

import fox.mods.MinersGrace;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroup {
    public static final ItemGroup MINERS_GRACE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MinersGrace.MOD_ID, "miners-grace"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.miners-grace"))
                    .icon(() -> new ItemStack(ModItems.METAL_DETECTOR_STAGE_1)).entries((displayContext, entries) -> {

                        entries.add(ModItems.METAL_DETECTOR_STAGE_1);
                        entries.add(ModItems.XP_EXTRACTOR);
                        entries.add(ModItems.MAGNIFIER);
                        entries.add(ModItems.COGWHEEL);
                        entries.add(ModItems.SUN_AMULET);


                    }).build());



    public static void registerItemGroup() {
        MinersGrace.LOGGER.info("Registering Item Groups for " +MinersGrace.MOD_ID);
    }
}
