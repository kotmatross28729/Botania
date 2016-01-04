package vazkii.botania.client.integration.jei.puredaisy;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.recipe.RecipePureDaisy;

import javax.annotation.Nonnull;
import java.util.List;

public class PureDaisyRecipeWrapper implements IRecipeWrapper {

    private List inputs = ImmutableList.of();
    private List outputs = ImmutableList.of();
    private List<FluidStack> fluidInputs = ImmutableList.of();
    private List<FluidStack> fluidOutputs = ImmutableList.of();

    public PureDaisyRecipeWrapper(RecipePureDaisy recipe) {
        if (recipe.getInput() instanceof String) {
            inputs = ImmutableList.of(OreDictionary.getOres(((String) recipe.getInput())));
        } else if (recipe.getInput() instanceof Block) {
            Block b = ((Block) recipe.getInput());
            if (FluidRegistry.lookupFluidForBlock(b) != null) {
                fluidInputs = ImmutableList.of(new FluidStack(FluidRegistry.lookupFluidForBlock(b), 1000));
            } else {
                inputs = ImmutableList.of(new ItemStack(b, 1, b.getMetaFromState(b.getDefaultState())));
            }
        }

        Block outBlock = recipe.getOutputState().getBlock();
        if (FluidRegistry.lookupFluidForBlock(outBlock) != null) {
            fluidOutputs = ImmutableList.of(new FluidStack(FluidRegistry.lookupFluidForBlock(outBlock), 1000));
        } else {
            outputs = ImmutableList.of(new ItemStack(outBlock, 1, outBlock.getMetaFromState(recipe.getOutputState())));
        }
    }

    @Override
    public List getInputs() {
        return inputs;
    }

    @Override
    public List getOutputs() {
        return outputs;
    }

    @Override
    public List<FluidStack> getFluidInputs() {
        return fluidInputs;
    }

    @Override
    public List<FluidStack> getFluidOutputs() {
        return fluidOutputs;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {}

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {}

}
