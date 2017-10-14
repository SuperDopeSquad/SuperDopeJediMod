package superdopesquad.superdopejedimod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GeneratorAddedRecipes 
{
    public static boolean shouldAddRecipe(Item parItem)
    {
        return 
                (  parItem == Items.ENCHANTED_BOOK
                || parItem == Items.IRON_HORSE_ARMOR 
                || parItem == Items.GOLDEN_HORSE_ARMOR 
                || parItem == Items.DIAMOND_HORSE_ARMOR 
                );
    }
    
    public static ItemStack[] getCraftingGrid(Item parItem)
    {
        // Initialize the result array
        ItemStack[] resultItemStackArray = new ItemStack[9];
        for(int j = 0;j<resultItemStackArray.length;j++)
        {
            resultItemStackArray[j] = null;
        }
        
        // Create deconstructing recipes for things that don't have crafting recipes
        if (parItem == Items.ENCHANTED_BOOK)
        {
            resultItemStackArray = new ItemStack[] {
                    null, new ItemStack(Items.LEATHER, 1, 0), null,
                    new ItemStack(Items.PAPER, 1, 0), new ItemStack(Items.PAPER, 1, 0),  
                          new ItemStack(Items.PAPER, 1, 0),
                    null, null, null
            };
        }
        // Even though horse armor has recipe, need to adjust the wool color when 
        // deconstructed
        else if (parItem == Items.IRON_HORSE_ARMOR)
        {
            return new ItemStack[] {
                    null,
                    null,
                    new ItemStack(Items.IRON_INGOT, 1, 0),
                    new ItemStack(Items.IRON_INGOT, 1, 0),
                    new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 15),
                    new ItemStack(Items.IRON_INGOT, 1, 0),
                    new ItemStack(Items.IRON_INGOT, 1, 0),
                    new ItemStack(Items.IRON_INGOT, 1, 0),
                    new ItemStack(Items.IRON_INGOT, 1, 0)
            };
        }
        else if (parItem == Items.GOLDEN_HORSE_ARMOR)
        {
            return new ItemStack[] {
                    null,
                    null,
                    new ItemStack(Items.GOLD_INGOT, 1, 0),
                    new ItemStack(Items.GOLD_INGOT, 1, 0),
                    new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 12),
                    new ItemStack(Items.GOLD_INGOT, 1, 0),
                    new ItemStack(Items.GOLD_INGOT, 1, 0),
                    new ItemStack(Items.GOLD_INGOT, 1, 0),
                    new ItemStack(Items.GOLD_INGOT, 1, 0)
            };
        }
        else if (parItem == Items.DIAMOND_HORSE_ARMOR)
        {
            return new ItemStack[] {
                    null,
                    null,
                    new ItemStack(Items.DIAMOND, 1, 0),
                    new ItemStack(Items.DIAMOND, 1, 0),
                    new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 11),
                    new ItemStack(Items.DIAMOND, 1, 0),
                    new ItemStack(Items.DIAMOND, 1, 0),
                    new ItemStack(Items.DIAMOND, 1, 0),
                    new ItemStack(Items.DIAMOND, 1, 0)
            };
        }
        return resultItemStackArray;
    }
}
