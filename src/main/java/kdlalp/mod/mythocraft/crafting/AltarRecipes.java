package kdlalp.mod.mythocraft.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kdlalp.mod.mythocraft.blocks.altar.InventoryAltarIn;
import kdlalp.mod.mythocraft.core.MythoPlayer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class AltarRecipes
{
	/** The static instance of this class */
    private static final AltarRecipes instance = new AltarRecipes();
    /** A list of all the recipes added */
    private List<IAltarRecipe> recipes = new ArrayList<IAltarRecipe>();

    /**
     * Returns the static instance of this class
     */
    public static final AltarRecipes getInstance()
    {
        /** The static instance of this class */
        return instance;
    }

    private AltarRecipes()
    {
    	//Standard recipes here
    }
    
    
    public IAltarRecipe findMatchingRecipe(InventoryAltarIn inv, EntityPlayer player)
    {
        for(int i = 0; i < this.recipes.size(); i++)
        {
            IAltarRecipe recipe = recipes.get(i);

            if(inv.getIchor() >= recipe.ichorRequired() && MythoPlayer.tier(player) >= recipe.tierRequired() && recipe.matches(player, inv))
            {
                return recipe;
            }
        }
        return null;
    }
    
    public void addRecipe(ItemStack p_92103_1_, int tierReward, int tierRequired, int ichorCost, Object ... p_92103_2_)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (p_92103_2_[i] instanceof String[])
        {
            String[] astring = (String[])((String[])p_92103_2_[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (p_92103_2_[i] instanceof String)
            {
                String s2 = (String)p_92103_2_[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap<Character, ItemStack> hashmap;

        for (hashmap = new HashMap<Character, ItemStack>(); i < p_92103_2_.length; i += 2)
        {
            Character character = (Character)p_92103_2_[i];
            ItemStack itemstack1 = null;

            if (p_92103_2_[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)p_92103_2_[i + 1]);
            }
            else if (p_92103_2_[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)p_92103_2_[i + 1], 1, 32767);
            }
            else if (p_92103_2_[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)p_92103_2_[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        this.recipes.add(new AltarCraftingRecipe(new ShapedRecipes(j, k, aitemstack, p_92103_1_), ichorCost, tierReward, tierRequired));
    }

    public void addShapelessRecipe(ItemStack p_77596_1_, int tierReward, int tierRequired, int ichorCost, Object ... p_77596_2_)
    {
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        Object[] aobject = p_77596_2_;
        int i = p_77596_2_.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.recipes.add(new AltarCraftingRecipe(new ShapelessRecipes(p_77596_1_, arraylist), ichorCost, tierReward, tierRequired));
    }

    /**
     * returns the List<> of all recipes
     */
    public List<IAltarRecipe> getRecipeList()
    {
        return this.recipes;
    }
}
