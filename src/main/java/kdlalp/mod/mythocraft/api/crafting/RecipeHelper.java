package kdlalp.mod.mythocraft.api.crafting;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeHelper
{
	/**
	 * Creates a shaped recipe using the same arguments as are used for creating a Crafting recipe 
	 */
    public static ShapedRecipes getShapedRecipe(ItemStack result, Object ... args)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (args[i] instanceof String[])
        {
            String[] astring = (String[])((String[])args[i++]);

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
            while (args[i] instanceof String)
            {
                String s2 = (String)args[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap<Character, ItemStack> hashmap;

        for (hashmap = new HashMap<Character, ItemStack>(); i < args.length; i += 2)
        {
            Character character = (Character)args[i];
            ItemStack itemstack1 = null;

            if (args[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)args[i + 1]);
            }
            else if (args[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)args[i + 1], 1, 32767);
            }
            else if (args[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)args[i + 1];
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

        return new ShapedRecipes(j, k, aitemstack, result);
    }

	/**
	 * Creates a shapeless recipe using the same arguments as are used for creating a Crafting recipe 
	 */
    public static ShapelessRecipes getShapelessRecipe(ItemStack result, Object ... args)
    {
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        Object[] aobject = args;
        int i = args.length;

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

        return new ShapelessRecipes(result, arraylist);
    }
}
