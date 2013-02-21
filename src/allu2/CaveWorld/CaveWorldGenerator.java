package allu2.CaveWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class CaveWorldGenerator extends ChunkGenerator {
	private int Height;
	private Logger Log;

	List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

	public List<BlockPopulator> getDefaultPopulators(World world) {
		return populators;
	}

	public Location getfixedSpawnLocation(World world, Random random) {
		return new Location(world, 0, 0, 0);
	}

	public CaveWorldGenerator(String id, Logger log) {
		Log = log;
		populators.add(new TreeGenerator());
		populators.add(new OreGenerator());
		populators.add(new GravelGenerator());
		populators.add(new DungeonGenerator());
		populators.add(new ClayGenerator());

		// Parse id
		if (id == null) {
			Height = 200;
		} else {
			try {
				Height = Integer.parseInt(id);
			} catch (NumberFormatException e) {
				Log.info("Invalid Height! Set to 16.");
				Height = 16;
			}
		}
	}

	void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
		if (result[y >> 4] == null) {
			result[y >> 4] = new byte[4096];
		}
		result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid; // Look into
																	// the
																	// tutorial
																	// ;)
	}

	@Override
	public byte[][] generateBlockSections(World world, Random random,
			int chunkX, int chunkZ, BiomeGrid biomeGrid) {
		byte[][] result = new byte[world.getMaxHeight() / 16][];
		int x, y, z;

		Random rand = new Random(world.getSeed());
		SimplexOctaveGenerator octave = new SimplexOctaveGenerator(rand, 19); // octave
																				// 'height'
																				// map

		octave.setScale(1 / 80.0);

		// Sets bedrock layer
		for (x = 0; x < 16; x++) {
			for (z = 0; z < 16; z++) {
				setBlock(result, x, 0, z, (byte) Material.BEDROCK.getId());
			}
		}
		for (x = 0; x < 16; x++) // Set water.
		{
			for (z = 0; z < 16; z++) {
				for (y = 190; y < Height + 5; y++) {
					setBlock(result, x, y, z,
							(byte) Material.STATIONARY_WATER.getId());
				}
			}
		}
		// Sets the stone layers
		for (x = 0; x < 16; x++) {
			for (y = 152; y < Height - 1; y++) {
				for (z = 0; z < 16; z++) {
					setBlock(result, x, y, z, (byte) Material.STONE.getId());
				}
			}
		}

		for (x = 0; x < 16; x++) {
			for (y = 1; y < Height - 98; y++) {
				for (z = 0; z < 16; z++) {
					setBlock(result, x, y, z, (byte) Material.STONE.getId());
				}
			}
		}

		// Sets the grass layers
		for (x = 0; x < 16; x++) {
			for (y = 199; y < Height; y++) {
				for (z = 0; z < 16; z++) {
					setBlock(result, x, y, z, (byte) Material.GRASS.getId());
				}
			}
		}
		octave.setScale(1 / 160.0);
		for (x = 0; x < 16; x++) // Set hills on the grass layer
		{

			for (z = 0; z < 16; z++) {
				double noise = octave.noise(x + chunkX * 16, z + chunkZ * 16,
						0.5, 0.5) * 30;
				for (y = 199; y < Height + noise; y++) {
					setBlock(result, x, y, z, (byte) Material.DIRT.getId());
				}
				if (world.getBiome(chunkX * 16, chunkZ * 16) == Biome.DESERT) {
					setBlock(result, x, y, z, (byte) Material.SAND.getId());
				} else {
					setBlock(result, x, y, z, (byte) Material.GRASS.getId());
					if(y<=206 & y >200){setBlock(result, x, y, z, (byte) Material.SAND.getId());}
				}
			}
		}
		octave.setScale(1 / 80.0);

		for (x = 0; x < 16; x++) // Set the cave roof
		{

			for (z = 0; z < 16; z++) {
				double noise = octave.noise(x + chunkX * 16, z + chunkZ * 16,
						0.5, 0.5) * 30;
				for (y = 152; y < Height - 70 + noise; y++) {
					setBlock(result, x, y, z, (byte) Material.AIR.getId());
				}
				setBlock(result, x, y, z, (byte) Material.STONE.getId());
			}
		}
		for (x = 0; x < 16; x++) {
			for (y = 103; y < Height - 93; y++) {
				for (z = 0; z < 16; z++) {
					setBlock(result, x, y, z,
							(byte) Material.STATIONARY_LAVA.getId());
				}
			}
		}

		for (x = 0; x < 16; x++) {

			for (z = 0; z < 16; z++) {
				double noise = octave.noise(x + chunkX * 16, z + chunkZ * 16,
						0.5, 0.5) * 30;
				for (y = 102; y < Height - 100 + noise; y++) {
					setBlock(result, x, y, z, (byte) Material.STONE.getId());
				}
				setBlock(result, x, y, z, (byte) Material.STONE.getId());
			}

		}

		return result;
	}
}
