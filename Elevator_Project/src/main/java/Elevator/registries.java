package Elevator;

public class registries {
	// ------------------SIMULATION RESET AND ERROR
		public static final int SIMULATION_RESET_COIL = 0;
		public static final int ELEVATOR_ERROR = 84;

		// ------------------DOORS REGISTRY
		public static final int MOTOR_OPEN = 13;
		public static final int MOTOR_CLOSE = 12;
		public static final int DOOR_IS_CLOSE_COIL = 81;
		public static final int DOOR_IS_OPEN_COIL = 80;

		// ------------------MOVEMENT REGISTRIES
		public static final int MOTOR_DOWN_V2 = 8;

		public static final int MOTOR_DOWN_V1 = 9;

		public static final int MOTOR_UP_V1 = 10;

		public static final int MOTOR_UP_V2 = 11;

		public static final int MOTOR_CRAWL_SPEED = 1;

		public static final int MOTOR_READY = 82;

		public static final int MOTOR_ON = 83;

		// ------------------FLOOR SENSOR REGISTRY
		// ------------------Floor 1
		public static final int LVL_1_SAFETY_LOW = 1;
		public static final int LVL_1_reached = 2;
		public static final int LVL_1_SAFETY_HIGH = 3;
		// ------------------Floor 2
		public static final int LVL_2_SAFETY_LOW = 9;
		public static final int LVL_2_reached = 10;
		public static final int LVL_2_SAFETY_HIGH = 11;

		// ------------------Floor 3
		public static final int LVL_3_SAFETY_LOW = 17;
		public static final int LVL_3_reached = 18;
		public static final int LVL_3_SAFETY_HIGH = 19;

		// ------------------Floor 4
		public static final int LVL_4_SAFETY_LOW = 25;
		public static final int LVL_4_reached = 26;
		public static final int LVL_4_SAFETY_HIGH = 27;

		// ------------------LEVEL 1 AND 2 INTERACTION
		public static final int LVL_1_TO_2 = 4;
		public static final int LVL_2_TO_1 = 8;

		// ------------------LEVEL 2 AND 3 INTERACTION
		public static final int LVL_2_TO_3 = 12;
		public static final int LVL_3_TO_2 = 16;

		// ------------------LEVEL 3 AND 4 INTERACTION
		public static final int LVL_3_TO_4 = 20;
		public static final int LVL_4_TO_3 = 24;
}
