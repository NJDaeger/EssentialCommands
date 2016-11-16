package com.njdaeger.essentials.enums;

public enum BanMessage {
	
	BANNED_NOREASON {
		@Override
		public String banNoReason() {
			return super.banNoReason();
		}
	},
	BANNED_REASON {
		@Override
		public String banReason(String reason) {
			return super.banReason(reason);
		}
	},
	TEMP_NOREASON {
		@Override
		public String banNoReason() {
			return super.banNoReason();
		}
	},
	TEMP_REASON {
		@Override
		public String banReason(String reason) {
			return super.banReason(reason);
		}
	};
	public String banReason(String reason) {
		return reason;
	}
	public String banNoReason() {
		return "The Ban Hammer has spoken!";
	}
	
}
