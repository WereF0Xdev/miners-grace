package fox.mods.utils;

import fox.mods.MinersGrace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTOR_STAGE_ONE_DETECTABLE_BLOCKS =
                createTag("metal_detector_stage_one_detectable_blocks");

        public static final TagKey<Block> METAL_DETECTOR_STAGE_TWO_DETECTABLE_BLOCKS =
                createTag("metal_detector_stage_two_detectable_blocks");

        public static final TagKey<Block> METAL_DETECTOR_STAGE_THREE_DETECTABLE_BLOCKS =
                createTag("metal_detector_stage_three_detectable_blocks");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MinersGrace.MOD_ID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MinersGrace.MOD_ID, name));
        }
    }
}
