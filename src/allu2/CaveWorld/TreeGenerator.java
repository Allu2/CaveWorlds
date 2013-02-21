package allu2.CaveWorld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;

public class TreeGenerator extends BlockPopulator {

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		int cX = chunk.getX() * 16;
		int cZ = chunk.getZ() * 16;
		int cZOff = cZ + random.nextInt(10);
		int cXOff = cX + random.nextInt(10);
		if (world.getBiome(cX, cZ) == Biome.JUNGLE) {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextInt(100) > 70 ? TreeType.JUNGLE
							: TreeType.JUNGLE_BUSH);
			while (true) {
				Random moi = new Random(world.getSeed());
				world.generateTree(
						new Location(world, cXOff+moi.nextInt(14), world.getHighestBlockYAt(
								cXOff, cZOff), cZOff+moi.nextInt(14)),
						random.nextInt(100) > 90 ? TreeType.JUNGLE
								: TreeType.JUNGLE_BUSH);
				if (moi.nextInt(100) > 30) {
					world.generateTree(
							new Location(world, cXOff+moi.nextInt(14), world
									.getHighestBlockYAt(cXOff, cZOff), cZOff+moi.nextInt(14)),
							random.nextInt(100) > 90 ? TreeType.JUNGLE
									: TreeType.JUNGLE_BUSH);
				}
				if (moi.nextInt(100) <= 30) {
					break;
				}
			}
		}
		if (world.getBiome(cX, cZ) == Biome.SWAMPLAND) {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextBoolean() ? TreeType.SWAMP : TreeType.SWAMP);
		}
		if (world.getBiome(cX, cZ) == Biome.PLAINS) {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextInt(100) > 80 ? TreeType.TREE
							: TreeType.BIG_TREE);
		}
		if (world.getBiome(cX, cZ) == Biome.FOREST) {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextInt(100) > 55 ? TreeType.TREE
							: TreeType.BIG_TREE);
		}
		if (world.getBiome(cX, cZ) == Biome.TAIGA) {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextInt(100) > 55 ? TreeType.REDWOOD
							: TreeType.TALL_REDWOOD);
		}
		if (world.getBiome(cX, cZ) == Biome.TAIGA) {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextInt(100) > 55 ? TreeType.REDWOOD
							: TreeType.TALL_REDWOOD);
		} else {
			world.generateTree(
					new Location(world, cXOff, world.getHighestBlockYAt(cXOff,
							cZOff), cZOff),
					random.nextInt(100) > 50 ? TreeType.TREE
							: TreeType.BIG_TREE);
		}
	}
}
