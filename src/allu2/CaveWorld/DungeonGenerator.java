package allu2.CaveWorld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DungeonGenerator extends BlockPopulator {
	void setBlock(World world, int x, int y, int z, Material type) {
		Block block = world.getBlockAt(x, y, z);
		block.setType(type);
	}
void setOre(World world, int x, int y, int z, Material type, Random random) {
	if (world.getBlockAt(x, y, z).isEmpty() == false
			& world.getBlockAt(x, y, z).isLiquid() == false) {
		setBlock(world, x, y, z, type);
		int adderx = 0;
		int adderz = 0;
		int addery = 0;		
		for (adderx = 0; adderx <= 8; adderx++)
		{
			for (adderz = 0; adderz <= 8; adderz++) {
				for (addery = 0; addery <=  + 8; addery++) {
					setBlock(world, x+adderx, y+addery, z+adderz, type);
				}
			}
		}
		for (adderx = 1; adderx < 8; adderx++)
		{
			for (adderz = 1; adderz < 8; adderz++) {
				for (addery = 1; addery <  + 8; addery++) {
					setBlock(world, x+adderx, y+addery, z+adderz, Material.AIR);
				}
			}
		}
		setBlock(world, x+4, y+1, z+4, Material.MOB_SPAWNER);
		Block blockki = world.getBlockAt(x+4, y+1, z+4);
		BlockState state = blockki.getState();
		Random rand = random;
		if(state instanceof CreatureSpawner){
			CreatureSpawner spawner = (CreatureSpawner) state;
			spawner.setSpawnedType(rand.nextBoolean() ? EntityType.SKELETON : EntityType.ZOMBIE);
		}
		setBlock(world, x+1, y+1, z+4, Material.CHEST);
		Block chest = world.getBlockAt(x+1, y+1, z+4);
		BlockState chesti = chest.getState();
		if(chesti instanceof Chest){
			
			Chest ch= (Chest) chesti;
			Inventory inv = ch.getInventory();
			ItemStack itemstack = null;
			if(rand.nextInt(1000)>500){
			itemstack = new ItemStack(Material.DIAMOND, 3);
			inv.addItem(itemstack);}
			if(rand.nextInt(1000)>500){
			itemstack = new ItemStack(Material.MELON_SEEDS, 3);
			inv.addItem(itemstack);}
			itemstack = new ItemStack(Material.PUMPKIN_SEEDS, 2);
			inv.addItem(itemstack);	
			itemstack = new ItemStack(Material.COOKIE, 2);
			inv.addItem(itemstack);	
			if(rand.nextInt(1000)>800){
			itemstack = new ItemStack(Material.SADDLE, 1);
			inv.addItem(itemstack);}
			itemstack = new ItemStack(Material.SUGAR_CANE, 2);
			inv.addItem(itemstack);	
			if(rand.nextInt(1000)>990){
				itemstack = new ItemStack(rand.nextInt(100)>80 ? Material.SPONGE : Material.SKULL, 1);
				inv.addItem(itemstack);	
			}
		}	
	}
		}
	@Override
	public void populate(World world, Random random, Chunk chunk) {


		int cX = chunk.getX() * 16;
		int cZ = chunk.getZ() * 16;
		int number = 0;
		Random randr = random;
		while (number <= 5) {

			int cZOff = cZ + randr.nextInt(15);
			int cXOff = cX + randr.nextInt(15);
			int Orepoint = randr.nextInt(250);
			int randomizer = randr.nextInt(200);
			if (randomizer <= 10) {
				setOre(world, cXOff, Orepoint, cZOff, Material.MOSSY_COBBLESTONE, random);
			}
			number = number+1;
	}

	}
}
