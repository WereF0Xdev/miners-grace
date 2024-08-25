package fox.mods.configuration;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "miners-grace")
@Config(name = "miners-grace-config", wrapperName = "MinersGraceConfig")
public class MinersGraceFileConfiguration {
    @SectionHeader("Items")
    public int ExperienceExtractorExperienceDrop = 1;

    @SectionHeader("Enchantments")
    public double AutoSmeltLevelOneSmeltingChance = 0.33;
    public double AutoSmeltLevelTwoSmeltingChance = 0.66;
    public double AutoSmeltLevelThreeSmeltingChance = 1;
}
