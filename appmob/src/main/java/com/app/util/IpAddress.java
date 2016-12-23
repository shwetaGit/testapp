package com.app.util;
import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class IpAddress {
	private static final int DEFAULT_NETMASK = 32; // 255.255.255.255
	public final static String DEFAULT_NETMASK_STRING = "255.255.255.0";
	private final BitSet bits = new BitSet(5); // To validate the IP Address

	private static final String VALIDVALUES = "128, 192, 224, 240, 248, 252";

	public static final int CLASS_A = 0;
	public static final int CLASS_B = 1;
	public static final int CLASS_C = 2;
	public static final int CLASS_D = 3;
	public static final int MULTI_CAST = 3;

	private static final long PRIVATE_STARTIP_A = 167772160l;
	private static final long PRIVATE_ENDIP_A = 184549375l;
	private static final long PRIVATE_STARTIP_B = 2886729728l;
	private static final long PRIVATE_ENDIP_B = 2887778303l;
	private static final long PRIVATE_STARTIP_C = 3232235520l;
	private static final long PRIVATE_ENDIP_C = 3232301055l;

	public static final long CLASS_A_START = IpAddress.convertToLong("1.0.0.0"); // 1.0.0.0
	public static final long CLASS_A_END = IpAddress.convertToLong("127.255.255.255"); // 127.255.255.255
	public static final long CLASS_B_START = IpAddress.convertToLong("128.0.0.0"); // 128.0.0.0
	public static final long CLASS_B_END = IpAddress.convertToLong("192.255.255.255"); // 191.255.255.255
	public static final long CLASS_C_START = IpAddress.convertToLong("192.0.0.0"); //
	public static final long CLASS_C_END = IpAddress.convertToLong("192.255.255.255"); // 191.255.255.255
	public static final long CLASS_D_START = IpAddress.convertToLong("224.0.0.0"); //
	public static final long CLASS_D_END = IpAddress.convertToLong("239.255.255.255"); //
	public static final long MULTICAST_START = IpAddress.convertToLong("224.0.0.0"); //
	public static final long MULTICAST_END = IpAddress.convertToLong("239.255.255.255"); //

	private final String IP;
	private final String IP_TO;
	private final long ipaddress;
	private long maxIPRange;
	private int port;
	private int maxPort;
	private int netmask = DEFAULT_NETMASK;
	private boolean ipRangeAvailable = false;
	private boolean portRangeAvailable = false;

	private IpAddress startHostIp = null;
	private IpAddress endHostIp = null;

	/**
	 * Empty Constructor to be used only when u want to convert IP (String) to
	 * long and avoid creating IPAddress object every time when u want to do a
	 * conversion.
	 */

	public IpAddress() {
		IP = "";
		IP_TO = "";
		ipaddress = -1;
		netmask = DEFAULT_NETMASK;
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to
	 * long if its a ipaddress rather than a host name.
	 * 
	 * @param String
	 *            ip
	 */

	public IpAddress(final String _ip) {
		if (_ip != null) {
			IP = _ip;
			ipaddress = convert(_ip);
		} else {
			IP = "";
			ipaddress = -1;
		}
		IP_TO = "";
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to
	 * long if its a ipaddress rather than a host name.
	 * 
	 * @param String
	 *            ip, int port
	 */

	public IpAddress(final String _ip, final int _port) {
		this(_ip);
		this.port = _port;
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to
	 * long if its a ipaddress rather than a host name. Second parameter is to
	 * set IP range.
	 * 
	 * @param String
	 *            ipStart, ipEnd
	 */

	public IpAddress(final String _ipStart, final String _ipEnd) {
		if ((_ipStart != null) && (_ipEnd != null)) {
			IP = _ipStart;
			IP_TO = _ipEnd;
			ipaddress = convert(_ipStart);
			maxIPRange = convert(_ipEnd);
			if (ipaddress > maxIPRange) {
				ipRangeAvailable = false;
			} else {
				ipRangeAvailable = true;
			}
		} else {
			IP = "";
			IP_TO = "";
			ipaddress = -1;
		}
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to
	 * long if its a ipaddress rather than a host name. Second parameter is to
	 * set IP range.
	 * 
	 * @param String
	 *            ipStart, ipEnd, int port
	 */

	public IpAddress(final String _ipStart, final String _ipEnd, final int _port) {
		this(_ipStart, _ipEnd);
		this.port = _port;
	}

	/**
	 * Set the max port value for the range
	 *
	 * @param int port
	 */

	public void setMaxPortValue(final int _maxport) {
		this.maxPort = _maxport;
		if (port > maxPort) {
			portRangeAvailable = false;
		} else {
			portRangeAvailable = true;
		}
	}

	public boolean isPrivateClassA() {
		if (ipaddress >= PRIVATE_STARTIP_A && ipaddress <= PRIVATE_ENDIP_A) {
			return true;
		}
		return false;
	}

	public boolean isPrivateClassB() {
		if (ipaddress >= PRIVATE_STARTIP_B && ipaddress <= PRIVATE_ENDIP_B) {
			return true;
		}
		return false;
	}

	public boolean isPrivateClassC() {
		if (ipaddress >= PRIVATE_STARTIP_C && ipaddress <= PRIVATE_ENDIP_C) {
			return true;
		}
		return false;
	}

	public boolean isPrivateIP() {

		if (isPrivateClassA() || isPrivateClassB() || isPrivateClassC()) {
			return true;
		}

		return false;
	}

	public boolean isClassA() {
		return ((ipaddress >= CLASS_A_START) && (ipaddress <= CLASS_A_END));
	}

	public boolean isClassB() {
		return ((ipaddress >= CLASS_B_START) && (ipaddress <= CLASS_B_END));
	}

	public boolean isClassC() {
		return ((ipaddress >= CLASS_C_START) && (ipaddress <= CLASS_C_END));
	}

	public boolean isClassD() {
		return ((ipaddress >= CLASS_D_START) && (ipaddress <= CLASS_D_END));
	}

	public boolean isMultiCast() {
		return isClassD();
	}

	public static boolean isPrivateIP(final long _ipaddress) {
		if ((_ipaddress >= PRIVATE_STARTIP_A && _ipaddress <= PRIVATE_ENDIP_A) || (_ipaddress >= PRIVATE_STARTIP_B && _ipaddress <= PRIVATE_ENDIP_B)
				|| (_ipaddress >= PRIVATE_STARTIP_C && _ipaddress <= PRIVATE_ENDIP_C)) {
			return true;
		}

		return false;
	}

	public static boolean isValidIPAddress(final String _ipAddress) {
		final long ipaddress = convertToLong(_ipAddress);
		return isValidIPAddress(ipaddress);
	}

	/**
	 * Check whether valid ip address. Returns true if valid.
	 *
	 * @return boolean
	 */

	public static boolean isValidIPAddress(final long _ipaddress) {
		return (_ipaddress > -1) ? true : false;
	}

	/**
	 * Check whether valid ip address. Returns true if valid.
	 *
	 * @return boolean
	 */

	public boolean isValidIPAddress() {
		return (ipaddress > -1) ? true : false;
	}

	/**
	 * Returns True if IP range available. (Default is false)
	 *
	 * @return boolean
	 */

	public boolean isIPRangeAvailable() {
		return ipRangeAvailable;
	}

	/**
	 * Returns True if Port range available. (Default is false)
	 *
	 * @return boolean
	 */

	public boolean isPortRangeAvailable() {
		return portRangeAvailable;
	}

	/**
	 * Returns the IP Max range if its set.
	 *
	 * @return String
	 */

	public String getIPAddressMaxRange() {
		return IP_TO;
	}

	/**
	 * Returns the IP Max Range as a long value
	 *
	 * @return long
	 */

	public long getIPAddressMaxRangeAsLong() {
		return maxIPRange;
	}

	/**
	 * Get the IP Address
	 *
	 * @return String ipaddress
	 */

	public String getIPAddress() {
		return IP;
	}

	/**
	 * Get the IP Address as a long value
	 *
	 * @return long ipaddress
	 */

	public long getIPAddressAsLong() {
		return ipaddress;
	}

	/**
	 * Get the port (Initial port value if a max value is set)
	 *
	 * @return int port
	 */

	public int getPort() {
		return port;
	}

	/**
	 * Get the max port value (Initial port value if a max value is set)
	 *
	 * @return int port
	 */

	public int getPortMaxRange() {
		return maxPort;
	}

	/**
	 * Check the port range.
	 * 
	 * @param int port
	 * @return boolean
	 */

	public boolean betweenPortRange(final int _port) {
		return ((_port >= this.port) && (_port <= this.maxPort));
	}

	/**
	 * Check IP Range. Returns true if found
	 *
	 * @param IPAddress
	 *            IP
	 * @return boolean
	 */

	public boolean betweenIPRange(final IpAddress _ipAddress) {
		if (_ipAddress == null) {
			return false;
		}
		return this.betweenIPRange(_ipAddress.ipaddress);
	}

	/**
	 * Check IP Range. Returns true if found
	 *
	 * @param long ipAddress
	 * @return boolean
	 */

	public boolean betweenIPRange(final long _ipAddress) {
		return ((_ipAddress >= this.ipaddress) && (_ipAddress <= this.maxIPRange));
	}

	/**
	 * Check IP Range. Returns true if found
	 *
	 * @param String
	 *            ipAddress
	 * @return boolean
	 */

	public boolean betweenIPRange(final String _ipAddress) {
		return betweenIPRange(convert(_ipAddress));
	}

	public static final long convertToLong(final String _ipAddress) {
		if (_ipAddress == null) {
			return -1;
		}
		final BitSet bits = new BitSet(5);
		long elements[] = new long[5];
		elements[4] = DEFAULT_NETMASK; // 255.255.255.255

		final StringTokenizer st = new StringTokenizer(_ipAddress, "./");
		for (byte i = 0; i < 5; i++) {
			try {
				elements[i] = Long.parseLong(st.nextToken());
				if ((elements[i] > -1) && (elements[i] < 256)) {
					bits.set(i);
				}
			} catch (NoSuchElementException ignored) {
			} catch (Exception e) {
				return -1;
			}
		}

		// return -1 if not a valid IP Address
		if (!bits.get(0) || !bits.get(1) || !bits.get(2) || !bits.get(3)) {
			return -1;
		}

		/**
		 * Logic for converting IP String to long (Bit operation is 3 times
		 * faster than multiplication).
		 * 
		 * Example. 146.127.98.12 a = 146 * 256 * 256 * 256; b = 127 * 256 *
		 * 256; c = 98 * 256; d = 12
		 * 
		 * ipAddress = a + b + c + d;
		 */

		return ((elements[0] << 24) + (elements[1] << 16) + (elements[2] << 8) + (elements[3]));

	}

	/**
	 * Convert IP Address (eg. 146.127.98.100 to 2457821796 ) from String to a
	 * long.
	 * 
	 * @param String
	 *            ipAddress
	 * @return long ip
	 */

	public final long convert(final String ipAddress) {

		if (ipAddress == null) {
			return -1;
		}
		bits.clear();
		long elements[] = new long[5];
		elements[4] = DEFAULT_NETMASK; // 255.255.255.255

		StringTokenizer st = new StringTokenizer(ipAddress, "./");
		for (byte i = 0; i < 5; i++) {
			try {
				elements[i] = Long.parseLong(st.nextToken());
				if ((elements[i] > -1) && (elements[i] < 256)) {
					bits.set(i);
				}
			} catch (NoSuchElementException ignored) {
			} catch (Exception e) {
				return -1;
			}
		}

		// return -1 if not a valid IP Address
		if (!bits.get(0) || !bits.get(1) || !bits.get(2) || !bits.get(3)) {
			return -1;
		}

		netmask = (int) elements[4];

		/**
		 * Logic for converting IP String to long (Bit operation is 3 times
		 * faster than multiplication).
		 * 
		 * Eg. 146.127.98.12 a = 146 * 256 * 256 * 256; b = 127 * 256 * 256; c =
		 * 98 * 256; d = 12
		 * 
		 * ipAddress = a + b + c + d;
		 */

		return ((elements[0] << 24) + (elements[1] << 16) + (elements[2] << 8) + (elements[3]));
	}

	/**
	 * Convert a long (number) to an ip address. For eg. 2457821796 to
	 * 146.127.98.100
	 *
	 * @param long ip
	 * @return String
	 */

	public static final String convertBack(final long ip) {
		// 4294967295 = 255.255.255.255
		if ((ip < 0) || (ip > 4294967295l)) {
			return "";
		}
		if (ip == 0) {
			return "0.0.0.0";
		}
		String binary = Long.toBinaryString(ip);
		int startx, endx, totalsize;
		totalsize = binary.length();
		endx = totalsize;
		int ipBlock[] = new int[4];
		for (int b = 3, block = 8; b >= 0; b--) {
			startx = totalsize - block;
			if (startx < 0) {
				startx = 0;
			}
			ipBlock[b] = Long.valueOf(binary.substring(startx, endx), 2).intValue();
			endx = startx;
			block += 8;

		}
		return new StringBuilder().append(ipBlock[0]).append(".").append(ipBlock[1]).append(".").append(ipBlock[2]).append(".").append(ipBlock[3]).toString();
	}

	/**
	 * Convert a netmask to the string notation. eg. 32 to 255.255.255.0
	 *
	 * @param int mask
	 * @return String
	 */

	public static String convertNetworkmask(int mask) {

		if (mask <= 0 || mask > 32) {
			return DEFAULT_NETMASK_STRING;
		}

		int array[] = new int[4];
		int noBits = 0;
		noBits = mask / 8;
		int i = 0;

		for (i = 0; i < noBits; i++) {
			array[i] = 255;
		}

		int rem = mask % 8;
		if (rem > 0) {
			array[i] = getHighBitValue(rem);
		}

		String dot = ".";
		StringBuilder sb = new StringBuilder();
		sb.append(array[0]).append(dot).append(array[1]).append(dot).append(array[2]).append(dot).append(array[3]);
		return sb.toString();

	}

	/**
	 * Converts ip mask notation to the int value. eg. 255.255.255.0 to 32
	 *
	 * @param int mask
	 * @return String
	 */

	public static int getNetworkMask(final String _ipNotationMask) throws Exception {

		if (_ipNotationMask == null) {
			return 0;
		}

		int array[] = new int[4];
		int mask = 0;
		try {
			StringTokenizer st = new StringTokenizer(_ipNotationMask, ".");
			array[0] = Integer.parseInt(st.nextToken());
			array[1] = Integer.parseInt(st.nextToken());
			array[2] = Integer.parseInt(st.nextToken());
			array[3] = Integer.parseInt(st.nextToken());

			int quad = 0;
			for (int i = 0; i < 4; i++) {
				if (array[i] < 255) {
					for (int j = i + 1; j < 4; j++) {
						if (array[j] > 0) {
							return 0;
						}
					}
				} else {
					quad++;
				}
			}
			if (quad < 4) {
				mask = (quad * 8) + getQuadValue(array[quad]);
			} else {
				mask = (quad * 8);
			}
		} catch (Exception ex) {
			throw new Exception("Invalid Mask Value, Valid values are " + VALIDVALUES);

		}
		return mask;
	}

	/**
	 * Get the network mask
	 *
	 * @return int
	 */

	public final int getNetworkMask() {
		return netmask;
	}

	/**
	 * Get the hash code of IPAddress (String)
	 *
	 * @return int
	 */

	public int hashCode() {
		return IP.hashCode();
	}

	/**
	 * Returns the IP Address (String)
	 *
	 * @return String
	 */

	public String toString() {
		return IP;
	}

	/**
	 * Implements the Comparable interface
	 */

	public int compareTo(Object o) {
		return compareTo((IpAddress) o);
	}

	/**
	 * Implements the Comparable interface
	 */

	public int compareTo(IpAddress ip) {
		return (int) (this.ipaddress - ip.ipaddress);
	}

	/**
	 * Implementation of equals
	 *
	 * @return boolean.
	 */

	public boolean equals(final Object o) {
		try {
			if (o instanceof String) {
				return IP.equals((String) o);
			} else if (o instanceof IpAddress) {
				return (this.ipaddress == (((IpAddress) o).ipaddress));
			}
		} catch (Exception ignored) {
		}
		return false;
	}

	public final boolean compareIPAddress(final IpAddress ip) {
		if (ip == null) {
			return false;
		}
		if (isIPRangeAvailable()) {
			if (betweenIPRange(ip.ipaddress)) {
				return comparePort(ip.port);
			}
		} else if (this.ipaddress == ip.ipaddress) {
			return comparePort(ip.port);
		}
		return false;
	}

	private boolean comparePort(int p) {
		if (port == 0) // Port check not required
		{
			return true;
		}
		if (isPortRangeAvailable()) {
			return betweenPortRange(p);
		}
		return (port == p);
	}

	public static int getHighBitValue(final int bits) {
		if (bits > 7) {
			return 255;
		}
		int value = 0;
		for (int i = 7; i > (7 - bits); i--) {
			value = value + new Double(Math.pow(2, i)).intValue();
		}
		return value;
	}

	private static int getQuadValue(int quadValue) throws Exception {
		if (quadValue > 255) {
			return 8;
		}

		switch (quadValue) {
		case 0:
			return 0;
		case 128:
			return 1;
		case 192:
			return 2;
		case 224:
			return 3;
		case 240:
			return 4;
		case 248:
			return 5;
		case 252:
			return 6;
		case 254:
			return 7;
		default:
			throw new Exception("Invalid Value");
		}
	}

	public void setNetworkMask(final String net) throws Exception {
		netmask = getNetworkMask(net);
	}

	public IpAddress getStartHostIp() throws Exception {
		if (startHostIp == null) {
			throw new Exception("Start Ip not set");
		}
		return startHostIp;
	}

	public void setStartHostIp(IpAddress ip) {
		this.startHostIp = ip;
	}

	public IpAddress getEndHostIp() throws Exception {
		if (endHostIp == null) {
			throw new Exception("End Ip not set");
		}
		return endHostIp;
	}

	public void setEndHostIp(IpAddress ip) {
		this.endHostIp = ip;
	}

	public static IpAddress getHostRange(final String networkAddress, final String netmask) throws Exception {
		IpAddress ip = new IpAddress(networkAddress);
		if (!ip.isValidIPAddress()) {
			throw new Exception("Invalid IP Address");
		}

		long ipLong = ip.getIPAddressAsLong();
		ip.setNetworkMask(netmask); // Netmask is Invalid if this statement
									// generates an Exception

		long mask = ip.convert(netmask);
		mask = (int) ~mask;
		long host = mask & ipLong; // And Op on network Address and inverted
									// mask yeilds host part
		long startIp = ipLong - host + 1; // substract number of host part from
		// IpAddress specified to get start Host

		long hosts = (long) (Math.pow(2.0, (32 - ip.getNetworkMask(netmask))));
		if (hosts < 3) {
			throw new Exception("Invalid Mask Value, Valid values are " + VALIDVALUES);
		}
		long endIp = startIp + hosts - 3;
		ip.setStartHostIp(new IpAddress(ip.convertBack(startIp)));
		ip.setEndHostIp(new IpAddress(ip.convertBack(endIp)));
		return ip;

	}

}
