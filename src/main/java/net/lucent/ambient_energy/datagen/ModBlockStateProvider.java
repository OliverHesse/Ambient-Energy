package net.lucent.ambient_energy.datagen;


import net.lucent.ambient_energy.AmbientEnergy;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AmbientEnergy.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }



    private void simpleBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void logMBlock(RotatedPillarBlock block) {
        this.axisBlock(block,
                this.blockTexture(block),
                ResourceLocation.fromNamespaceAndPath("minecraft", "block/oak_log_top")
        );
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void translucentBlockWithItem(DeferredBlock<?> deferredBlock) {
        Block block = deferredBlock.get();
        String path = key(deferredBlock).getPath();

        // Create cubeAll-like model with translucent render type
        ModelFile model = models().withExistingParent(path, mcLoc("block/cube_all"))
                .texture("all", blockTexture(block))
                .renderType("translucent"); // NeoForge 1.21.1 uses renderType() method

        // Generate block state and item model
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }

    // Helper method to get ResourceLocation key from DeferredBlock
    private ResourceLocation key(DeferredBlock<?> deferredBlock) {
        return deferredBlock.getId();
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("AmbientEnergy:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("AmbientEnergy:block/" + deferredBlock.getId().getPath() + appendix));
    }
}