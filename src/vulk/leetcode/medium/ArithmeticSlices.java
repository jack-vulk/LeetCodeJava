package vulk.leetcode.medium;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.medium.ArithmeticSlices.Param;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class ArithmeticSlices extends Solution<Param, Integer> {

	private static final int PARAM_INPUT = 1;
	private static final int PARAM_EXPECTED_OUTPUT = 2;

	public int numberOfArithmeticSlices(int[] nums) {

		int foundSlices = 0;

		if (nums == null || nums.length < 3) {
			return foundSlices;
		}

		int currentDiff = Math.abs(nums[1] - nums[0]);
		int subLength = 2;
		int subArthimeticLength = 0;

		for (int index = 2; index < nums.length; index++) {

			int newDiff = Math.abs(nums[index] - nums[index - 1]);

			if (newDiff == currentDiff) {
				subLength++;
			} else {
				currentDiff = newDiff;
				subLength = 2;
			}
		}

		return foundSlices;

	}

	public int hammingWeight(int n) {

		long longN = n < 0 ? n & (-1L >>> 32) : n;
		int trueBitCounts = 0;

		while (n > 0) {

			trueBitCounts += (n % 2 == 0 ? 0 : 1);

			n = n / 2;

		}

		double[][] glasses = new double[100][100];
		Map<Integer, Integer> map = new TreeMap<>();
		int a = map.getOrDefault("", 0);

		long aa = minimalKSum(new int[] { 39449347, 929454168, 195395484, 972092544, 832565816, 55518202, 869529363,
				130519314, 437532888, 773404909, 240432620, 878976907, 557112285, 339919839, 921927250, 421864947,
				434917412, 160770996, 438172899, 217409043, 892667515, 363928030, 187108898, 933784991, 180742167,
				967541078, 722686747, 861311043, 481513072, 743526320, 615942084, 698152299, 563338070, 803757477,
				583310522, 50260135, 965707628, 242725673, 150589128, 140228043, 519175687, 544077207, 318599137,
				939871865, 410264114, 764935246, 164325549, 510794164, 321107474, 259309135, 150045134, 297236966,
				454715798, 92493315, 458198984, 520950216, 639990948, 278255193, 859725239, 480665633, 579776574,
				900812501, 103356475, 800589393, 932642593, 911919909, 162255911, 675263580, 397900934, 385513053,
				300497893, 334160923, 164910236, 373666308, 896951060, 787093663, 126739634, 110109473, 528712961,
				704091652, 401047296, 221301151, 809581802, 779697340, 642783504, 584553133, 381360226, 624648083,
				689079060, 157668929, 748251768, 942039257, 448492826, 169138754, 689940256, 651123388, 588296794,
				714791735, 211859859, 673827992, 738103985, 67846687, 541759596, 140583926, 849622316, 275559442,
				343518880, 372330520, 661065922, 272175386, 44238005, 765719961, 978581551, 82401417, 616999800,
				507281831, 322929739, 601883805, 394927908, 382844972, 580746399, 985127047, 614649019, 499315442,
				883645408, 415550635, 930585815, 609913967, 859861539, 804826538, 59602588, 43651344, 908723246,
				364321762, 936706816, 270240622, 718212067, 834279569, 137996023, 941991992, 821786108, 769591118,
				642547928, 273036561, 742079077, 326903904, 939551239, 998640536, 447296379, 806827112, 622690141,
				523184738, 682295648, 954436734, 812960135, 203257494, 319253019, 987900549, 816213592, 774373045,
				44812586, 899325081, 444079128, 694309742, 295426316, 904667039, 170801566, 65949784, 822409524,
				365697892, 295472692, 678090637, 725725346, 905985914, 650101791, 957954059, 527766506, 770315775,
				688587589, 362729527, 696107838, 332532357, 729967998, 651749390, 148723690, 926668767, 83344031,
				463178117, 74709476, 53093176, 249311292, 142778915, 776663972, 557975992, 216599009, 350039495,
				631630733, 145522115, 722658997, 517110732, 484300857, 920201123, 427169010, 102103243, 159400305,
				248728636, 541625198, 316771235, 629798082, 101455106, 944841047, 205602545, 509831659, 230773134,
				148489271, 995818804, 266078204, 740878711, 739026837, 576592031, 679653876, 678719169, 637211856,
				295714710, 254977126, 184960666, 228195035, 593073751, 686757497, 876955680, 556233742, 893462346,
				926899343, 36535460, 832523191, 771458986, 979114992, 264772137, 270644681, 773167159, 815470028,
				364976417, 855799713, 434754209, 192948804, 305469507, 883212644, 883801416, 382389767, 652078996,
				443766102, 562889598, 95397949, 971663197 }, 670399);
		

		boolean check = areAlmostEqual("siyolsdcjthwsiplccjbuceoxmpjgrauocx", "siyolsdcjthwsiplccpbuceoxmjjgrauocx");


		List<Double> prevRow = new ArrayList<>(List.of((double) 10));		
		return trueBitCounts;




	}

	public boolean areAlmostEqual(String s1, String s2) {

		final char BASE = 'a';

		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		Set<Integer>[] c1Index = new HashSet[26];
		Set<Integer>[] c2Index = new HashSet[26];
		Arrays.setAll(c1Index, element -> new HashSet());
		Arrays.setAll(c2Index, element -> new HashSet());

		for (int cIndex = 0; cIndex < c1.length; cIndex++) {

			c1Index[c1[cIndex] - BASE].add(cIndex);
			c2Index[c2[cIndex] - BASE].add(cIndex);

		}

		boolean isEqual = true;
		Set<Integer> foundEqual = null;

		for (int cIndex = 0; cIndex < c1.length; cIndex++) {

			if (c1[cIndex] != c2[cIndex]) {

				Set<Integer> currentC1Index = c1Index[c1[cIndex] - BASE];
				Set<Integer> currentC2Index = c2Index[c2[cIndex] - BASE];
				Set<Integer> swapC1Index = c1Index[c2[cIndex] - BASE];
				Set<Integer> swapC2Index = c2Index[c1[cIndex] - BASE];

				if (!(swapC2Index.equals(swapC1Index) && currentC2Index.equals(currentC1Index))) {
					isEqual = false;
					break;
				} else if (foundEqual != null && !foundEqual.equals(swapC1Index)) {
					isEqual = false;
					break;
				} else if (foundEqual == null) {
					foundEqual = currentC1Index;
				}

			}

		}

		return isEqual;

	}

	public boolean isHappy(int n) {

		Set<Integer> c1Index = new HashSet();
		;
		c1Index.add(1);
		c1Index.add(2);
		Set<Integer> c2Index = new HashSet<>();
		c2Index.add(1);

		c2Index.contains(c1Index);
		c1Index.containsAll(c2Index);
		return c1Index.equals(c2Index);
	}

	private long calc(long val) {

		char[] digits = String.valueOf(val).toCharArray();

		long sumSquare = 0;

		for (char digit : digits)
			sumSquare += Math.pow((digit - '0'), 2);

		return sumSquare;
	}

	public long minimalKSum(int[] nums, int k) {

		Arrays.sort(nums);
		long sumK = 0;
		int num = 1;
		int numIndex = 1;
		int end = nums[0] - 1;

		// Retrieve sum ap from 1 to the first num
		int n = (nums[0] - 1) - num + 1;
		if (n > k) {
			n = k;
			end = num + n - 1;
		}
		if (n > 0) {
			sumK += getSumAp(num, end, n);
			k -= (n);
		}
		num = nums[0] + 1;

		while (k > 0 && numIndex < nums.length) {

			n = nums[numIndex] - nums[numIndex - 1] - 1;
			if (n > 0) {
				end = nums[numIndex] - 1;
				if (n > k) {
					n = k;
					end = nums[numIndex - 1] + n;
				}
				sumK += getSumAp(nums[numIndex - 1] + 1, end, n);
				k -= n;
			}
			num = nums[numIndex] + 1;
			numIndex++;
		}

		if (k > 0) {
			sumK += getSumAp(num, num + k - 1);
		}

		return sumK;

	}

	private long getSumAp(int start, int end) {

		return getSumAp(start, end, end - start + 1);

	}

	private long getSumAp(int start, int end, int n) {

		return n * (Long.valueOf(start + end)) / 2;
	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected Integer proc(Param param) throws ExecutionException {
		try {
			// return numberOfArithmeticSlices(param.nums);
			// return hammingWeight(00000000000000000000000000001011);
			// return hammingWeight(11);
			return hammingWeight(-3);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String filePath) {

		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		try {
			CsvParser.parse(filePath, new ICsvParser() {

				@Override
				public void readLine(int line, List<String> data) {

					switch (line) {
					case PARAM_INPUT: {
						info.params.nums = data.stream().mapToInt(Integer::parseInt).toArray();
						break;
					}
					case PARAM_EXPECTED_OUTPUT: {
						info.expectedValue = Integer.parseInt(data.get(0));
						break;
					}
					}

				}
			});
		} catch (IOException e) {
			throw new InvalidParameterException();
		}

		return info;
	}

	/**
	 * Param for this solution
	 * 
	 * @author KJack
	 *
	 */
	protected static class Param {

		private int[] nums;

		@Override
		public String toString() {

			return Arrays.toString(nums);
		}

	}
}
