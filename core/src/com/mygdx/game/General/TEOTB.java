package com.mygdx.game.General;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.Pantallas.Pantalla1;

public class TEOTB extends Game {

	private AssetManager manager;

	/*public static void main (String[] args) throws Exception {
		TexturePacker.process("C:\\Users\\abran\\Desktop\\pruebafusion", "C:\\Users\\abran\\Desktop\\pruebafusion\\fusion", "llevar");
	}*/

	public AssetManager getManager() {
		return manager;
	}


	@Override
	public void create () {
		setScreen(new Pantalla1(this));
	}

}
