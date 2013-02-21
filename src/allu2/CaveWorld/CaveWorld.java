package allu2.CaveWorld;

import java.util.logging.Logger;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class CaveWorld extends JavaPlugin
{
	Logger	Log;

	@Override
	public void onEnable()
	{
		Log = this.getLogger();
	}

	// Id are the params from bukkit.yml
	// Look in the docu, how to set params
	// In this case, id is the height of the generated world
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return new CaveWorldGenerator(id, Log);
	}
}