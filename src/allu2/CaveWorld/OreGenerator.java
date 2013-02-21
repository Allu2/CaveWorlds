package allu2.CaveWorld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class OreGenerator extends BlockPopulator {
	void setBlock(World world, int x, int y, int z, Material type) {
		Block block = world.getBlockAt(x, y, z);
		block.setType(type);
	}

	void setOre(World world, int x, int y, int z, Material type, Random random) {
		int vein_max_size = 15;
		int vein_size = 0;

		if (world.getBlockAt(x, y, z).isEmpty() == false
				& world.getBlockAt(x, y, z).isLiquid() == false) {
			setBlock(world, x, y + 3, z, Material.GLOWSTONE);
			setBlock(world, x, y, z, type);
			while (vein_size < vein_max_size) {
				int addings = random.nextInt(2) - 1;
				setBlock(world, x, y, z, type);
				x = x + addings;
				addings = random.nextInt(2) - 1;
				setBlock(world, x, y, z, type);
				y = y + addings;
				addings = random.nextInt(2) - 1;
				setBlock(world, x, y, z, type);
				z = z + addings;
				setBlock(world, x, y, z, type);
				if (random.nextInt(1000) < 300) {
					break;
				}
				vein_size = vein_size + 1;

			}
		}
	}

	@Override
	public void populate(World world, Random random, Chunk chunk) {

		int cX = chunk.getX() * 16;
		int cZ = chunk.getZ() * 16;
		int number = 0;
		Random randr = random;
		while (number < 50) {

			int cZOff = cZ + randr.nextInt(15);
			int cXOff = cX + randr.nextInt(15);
			int Orepoint = randr.nextInt(250) + 40;
			if (Orepoint < 102 & random.nextBoolean()) {
				setOre(world, cXOff, Orepoint, cZOff,
						random.nextBoolean() ? Material.EMERALD_ORE
								: Material.DIAMOND_ORE, random);

			}
			if (Orepoint < 102 & random.nextBoolean()) {
				setOre(world, cXOff, Orepoint, cZOff, Material.REDSTONE_ORE,
						random);
			}
			if (Orepoint > 102 & Orepoint < 200 & random.nextBoolean()) {
				setOre(world, cXOff, Orepoint, cZOff,
						random.nextBoolean() ? Material.IRON_ORE
								: Material.COAL_ORE, random);
			}
			if (Orepoint > 102 & Orepoint < 200 & random.nextBoolean()) {
				setOre(world, cXOff, Orepoint, cZOff,
						random.nextBoolean() ? Material.GOLD_ORE
								: Material.LAPIS_ORE, random);
			}
			number = number + 1;
		}
	}

}
