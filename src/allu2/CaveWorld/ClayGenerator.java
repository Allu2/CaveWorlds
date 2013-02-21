package allu2.CaveWorld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class ClayGenerator extends BlockPopulator {
	void setBlock(World world, int x, int y, int z, Material type) {
		Block block = world.getBlockAt(x, y, z);
		block.setType(type);
	}
void setOre(World world, int x, int y, int z, Material type) {
	int height = y;
	int adderx = 0;
	int addery = 0;
	int adderz = 0;
	if (world.getBlockAt(x, y, z).isEmpty() == false & world.getBlockAt(x, y, z).isLiquid() == false) {
		setBlock(world, x, y, z, type);
		for (adderx = 0; adderx < 5; adderx++)
		{
			for (adderz = 0; adderz < 6; adderz++) {
				for (addery = height; addery < height + 2; addery++) {
					setBlock(world, x+adderx, addery, z+adderz, type);
				}
			}
		}
	}
		}
@Override
public void populate(World world, Random random, Chunk chunk) {
	// TODO Auto-generated method stub

	int cX = chunk.getX() * 16;
	int cZ = chunk.getZ() * 16;
	int number = 0;

	while (number < 10) {
		Random randr = new Random();
		int cZOff = cZ + randr.nextInt(15);
		int cXOff = cX + randr.nextInt(15);
		int Orepoint = 198;
		int randomizer = randr.nextInt(250);
		if (randomizer <= 90) {
			setOre(world, cXOff, Orepoint, cZOff, Material.CLAY);
		}
		number = number+1;
}

}}
