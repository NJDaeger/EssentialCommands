package com.njdaeger.essentials.bannermanager;


/**
 * This specifies what layer the player is on when creating the banner.
 * @author Noah
 *
 */
public enum Layer {
	
	
	/**
	 * Represents Layer one.
	 * @author Noah
	 *
	 */
	L1 {
		@Override
		public Layer getLayer() {
			return L1;
		}

		@Override
		public boolean isonLayer(String player) {
			if (Banner.Layer1.contains(player)) {
				return true;
			}
			return false;
		}

		@Override
		public String listUsers() {
			if (Banner.Layer1 != null) {
				return Banner.Layer1.toString();
			}
			return null;
		}
	},
	
	/**
	 * Represents Layer two.
	 * @author Noah
	 *
	 */
	L2 {
		@Override
		public Layer getLayer() {
			// TODO Auto-generated method stub
			return L2;
		}

		@Override
		public boolean isonLayer(String player) {
			if (Banner.Layer2.contains(player)) {
				return true;
			}
			return false;
		}

		@Override
		public String listUsers() {
			if (Banner.Layer2 != null) {
				return Banner.Layer2.toString();
			}
			return null;
		}
	},
	
	/**
	 * Represents Layer three.
	 * @author Noah
	 *
	 */
	L3 {
		@Override
		public Layer getLayer() {
			// TODO Auto-generated method stub
			return L3;
		}

		@Override
		public boolean isonLayer(String player) {
			if (Banner.Layer3.contains(player)) {
				return true;
			}
			return false;
		}

		@Override
		public String listUsers() {
			if (Banner.Layer3 != null) {
				return Banner.Layer3.toString();
			}
			return null;
		}
	},
	
	/**
	 * Represents Layer four.
	 * @author Noah
	 *
	 */
	L4 {
		@Override
		public Layer getLayer() {
			// TODO Auto-generated method stub
			return L4;
		}

		@Override
		public boolean isonLayer(String player) {
			if (Banner.Layer4.contains(player)) {
				return true;
			}
			return false;
		}

		@Override
		public String listUsers() {
			if (Banner.Layer4 != null) {
				return Banner.Layer4.toString();
			}
			return null;
		}
	},
	
	/**
	 * Represents Layer five.
	 * @author Noah
	 *
	 */
	L5 {
		@Override
		public Layer getLayer() {
			// TODO Auto-generated method stub
			return L5;
		}

		@Override
		public boolean isonLayer(String player) {
			if (Banner.Layer5.contains(player)) {
				return true;
			}
			return false;
		}

		@Override
		public String listUsers() {
			if (Banner.Layer5 != null) {
				return Banner.Layer5.toString();
			}
			return null;
		}
	},
	
	/**
	 * Represents Layer six.
	 * @author Noah
	 *
	 */
	L6 {
		@Override
		public Layer getLayer() {
			return L6;
		}

		@Override
		public boolean isonLayer(String player) {
			if (Banner.Layer6.contains(player)) {
				return true;
			}
			return false;
		}

		@Override
		public String listUsers() {
			if (Banner.Layer6 != null) {
				return Banner.Layer6.toString();
			}
			return null;
		}
	};
	
	
	public Layer getLayer(Layer layer) {
		return layer.getLayer();
	}
	/**
	 * List all the users in specified layer.
	 * @return
	 */
	public abstract String listUsers();
	/**
	 * Get the layer.
	 * @return
	 */
	public abstract Layer getLayer();
	/**
	 * Looks for a player on the specified layer ArrayList.
	 * @param player Player to look for in the layer ArrayList.
	 * @return
	 */
	public abstract boolean isonLayer(String player);
	/*
	 * Possible Re-Write Plan
	 * 
	 * keep the bannerGUI class as is and add the rest of the methods.
	 * 		Contain actual clicking logic in the separate classes/methods.
	 * 
	 * Make 6 arraylists that contain what layer the player is on. 
	 * 		Maybe do this thing. MainGUI.listen(e).
	 * 
	 * 
	 * 
	 */
}
