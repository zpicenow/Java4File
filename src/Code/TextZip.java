package Code;

import java.io.*;
import java.util.*;

/**
 可以为这个类添加额外的方法及数据成员.

 @author  	赵鹏
 @version	2018/7/12
 **/

public class TextZip {

	//ID, 该学号的值需要修改!
	private static final String ID = "201692185";
	private static Map<Character, String> value_table = new HashMap<Character, String>();

	/**
	 * This method generates the huffman tree for the text: "abracadabra!"
	 *
	 * @return the root of the huffman tree
	 */

	public static TreeNode abracadbraTree() {
		TreeNode n0 = new TreeNode(new CharFreq('!', 1));
		TreeNode n1 = new TreeNode(new CharFreq('c', 1));
		TreeNode n2 = new TreeNode(new CharFreq('\u0000', 2), n0, n1);
		TreeNode n3 = new TreeNode(new CharFreq('r', 2));
		TreeNode n4 = new TreeNode(new CharFreq('\u0000', 4), n3, n2);
		TreeNode n5 = new TreeNode(new CharFreq('d', 1));
		TreeNode n6 = new TreeNode(new CharFreq('b', 2));
		TreeNode n7 = new TreeNode(new CharFreq('\u0000', 3), n5, n6);
		TreeNode n8 = new TreeNode(new CharFreq('\u0000', '7'), n7, n4);
		TreeNode n9 = new TreeNode(new CharFreq('a', 5));
		TreeNode n10 = new TreeNode(new CharFreq('\u0000', 12), n9, n8);
		return n10;
	}

	/**
	 * This method decompresses a huffman compressed text file.  The compressed
	 * file must be read one bit at a time using the supplied BitReader, and
	 * then by traversing the supplied huffman tree, each sequence of compressed
	 * bits should be converted to their corresponding characters.  The
	 * decompressed characters should be written to the FileWriter
	 *
	 * @param br the BitReader which reads one bit at a time from the
	 *           compressed file
	 *           huffman the huffman tree that was used for compression, and
	 *           hence should be used for decompression
	 *           fw      a FileWriter for storing the decompressed text file
	 */
	public static void decompress(BitReader br, TreeNode huffman, FileWriter fw) throws Exception {    //解码

		// IMPLEMENT THIS METHOD
		//	List<Boolean> list  = new ArrayList<Boolean>();
		TreeNode temp = huffman;
		List<Boolean> list = new ArrayList<Boolean>();  //用来存code
		while (br.hasNext()) {
			list.add(br.next());
		}
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i)) {                         //为false则是左子树
				temp = temp.getLeft();
				if (temp.isLeaf()) {                    //是叶子结点则写入字符
					fw.write(((CharFreq) temp.getItem()).getChar());
					temp = huffman;                     //重置为根结点
				}
			} else {                                    //右子树
				temp = temp.getRight();
				if (temp.isLeaf()) {
					fw.write(((CharFreq) temp.getItem()).getChar());
					temp = huffman;
				}
			}
		}
	}

	/**
	 * This method traverses the supplied huffman tree and prints out the
	 * codes associated with each character//         打印每一个字母的编码
	 *
	 * @param t the root of the huffman tree to be traversed
	 *          code a String used to build the code for each character as
	 *          the tree is traversed recursively
	 */
	public static void traverse(TreeNode t, String code) {    //递归调用
		// IMPLEMENT THIS METHOD
		if (t.isLeaf()) {
			value_table.put(((CharFreq) t.getItem()).getChar(), code);
			System.out.println(((CharFreq) t.getItem()).getChar() + "  前缀是  " + code);
		} else {
			traverse(t.getLeft(), code + "0");
			traverse(t.getRight(), code + "1");
		}

	}

	/**
	 * This method removes the TreeNode, from an ArrayList of TreeNodes,  which
	 * contains the smallest item.  The items stored in each TreeNode must
	 * implement the Comparable interface.
	 * The ArrayList must contain at least one element.
	 *
	 * @param a an ArrayList containing TreeNode objects
	 * @return the TreeNode in the ArrayList which contains the smallest item.
	 * This TreeNode is removed from the ArrayList.
	 */
	public static TreeNode removeMin(ArrayList a) {
		int minIndex = 0;
		for (int i = 0; i < a.size(); i++) {
			TreeNode ti = (TreeNode) a.get(i);
			TreeNode tmin = (TreeNode) a.get(minIndex);
			if (((Comparable) (ti.getItem())).compareTo(tmin.getItem()) < 0)
				minIndex = i;
		}
		TreeNode n = (TreeNode) a.remove(minIndex);
		return n;
	}

	/**
	 * This method counts the frequencies of each character in the supplied
	 * FileReader, and produces an output text file which lists (on each line)
	 * each character followed by the frequency count of that character.  This
	 * method also returns an ArrayList which contains TreeNodes.  The item stored
	 * in each TreeNode in the returned ArrayList is a CharFreq object, which
	 * stores a character and its corresponding frequency
	 *
	 * @param fr the FileReader for which the character frequencies are being
	 *           counted
	 *           pw the PrintWriter which is used to produce the output text file
	 *           listing the character frequencies
	 * @return the ArrayList containing TreeNodes.  The item stored in each
	 * TreeNode is a CharFreq object.
	 */
	public static ArrayList countFrequencies(FileReader fr, PrintWriter pw) throws Exception {
		// IMPLEMENT THIS METHOD
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		Map<Character, Integer> map = new HashMap<Character, Integer>();    //每个键值对存储相应字符和对应的出现次数
		while (true) {
			int i = fr.read();
			if (i == -1) break;
			else {
				char temp = (char) i;
				if (map.get(temp) != null) {    //Map中找到就次数加一
					int key = map.get(temp);
					key++;
					map.put(temp, key);
				} else {
					map.put(temp, 1);           //没有找到就新增
				}
			}
		}
		Set<Character> set = map.keySet();
		Iterator<Character> iterator = set.iterator();
		while (iterator.hasNext()) {        //写入freq
			char k = iterator.next();
			if (k == '\r') {
				pw.println("\\r" + " " + map.get(k));
			} else if (k == '\n') {
				pw.println("\\n" + " " + map.get(k));
			} else {
				pw.println(k + " " + map.get(k));
			}

			list.add(new TreeNode(new CharFreq(k, map.get(k))));        //加入结点
		}
		return list;

	}

	/**
	 * This method builds a huffman tree from the supplied ArrayList of TreeNodes.
	 * Initially, the items in each TreeNode in the ArrayList store a CharFreq object.
	 * As the tree is built, the smallest two items in the ArrayList are removed,
	 * merged to form a tree with a CharFreq object storing the sum of the frequencies
	 * as the root, and the two original CharFreq objects as the children.  The right
	 * child must be the second of the two elements removed from the ArrayList (where
	 * the ArrayList is scanned from left to right when the minimum element is found).
	 * When the ArrayList contains just one element, this will be the root of the
	 * completed huffman tree.
	 *
	 * @param trees the ArrayList containing the TreeNodes used in the algorithm
	 *              for generating the huffman tree
	 * @return the TreeNode referring to the root of the completed huffman tree
	 */
	public static TreeNode buildTree(ArrayList<TreeNode> trees) throws IOException {
		TreeNode parent = null;
		// IMPLEMENT THIS METHOD
		while (trees.size() > 1) {
			Collections.sort(trees, new Comparator<TreeNode>() {
				//先根据freq从小到大排序
				@Override
				public int compare(TreeNode o1, TreeNode o2) {
					// TODO Auto-generated method stub
					CharFreq c1 = (CharFreq) o1.getItem();
					CharFreq c2 = (CharFreq) o2.getItem();
					return c1.getFreq() - c2.getFreq();
				}
			});
			//左右结点分别为最小的和次小的
			TreeNode left = (TreeNode) trees.get(0);
			TreeNode right = (TreeNode) trees.get(1);
			parent = new TreeNode(new CharFreq('\u0000', ((CharFreq) (left.getItem())).getFreq()
					+ ((CharFreq) (right.getItem())).getFreq()));
			parent.setLeft(left);
			parent.setRight(right);
			removeMin(trees);
			removeMin(trees);
			trees.add(parent);
		}
		return trees.get(0);    //根节点为最大的，也就是最后一个
	}

	/**
	 * This method compresses a text file using huffman encoding.  Initially, the
	 * supplied huffman tree is traversed to generate a lookup table of codes for
	 * each character.  The text file is then read one character at a time, and
	 * each character is encoded by using the lookup table.  The encoded bits for
	 * each character are written one at a time to the specified BitWriter.
	 *
	 * @param fr the FileReader which contains the text file to be encoded
	 *           huffman the huffman tree that was used for compression, and
	 *           hence should be used for decompression
	 *           bw      the BitWriter used to write the compressed bits to file
	 */

	public static void compress(FileReader fr, TreeNode huffman, BitWriter bw) throws Exception {   //压缩

		// IMPLEMENT THIS METHOD
		traverse(huffman, "");
		while (true) {
			int i = fr.read();
			if (i == -1) break;
			else {
				String data = value_table.get((char) i);    //找到对应的前缀
				for (int j = 0; j < data.length(); j++) {
					boolean k = (data.charAt(j) == '1');
					bw.writeBit(k);                         //写入压缩文件
				}
			}
		}
	}

	/**
	 * This method reads a frequency file (such as those generated by the
	 * countFrequencies() method) and initialises an ArrayList of TreeNodes
	 * where the item of each TreeNode is a CharFreq object storing a character
	 * from the frequency file and its corresponding frequency.  This method provides
	 * the same functionality as the countFrequencies() method, but takes in a
	 * frequency file as parameter rather than a text file.
	 *
	 * @param inputFreqFile the frequency file which stores characters and their
	 *                      frequency (one character per line)
	 * @return the ArrayList containing TreeNodes.  The item stored in each
	 * TreeNode is a CharFreq object.
	 */
	public static ArrayList readFrequencies(String inputFreqFile) throws Exception {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFreqFile));
		BufferedReader buffer = new BufferedReader(reader);
		ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
		StringBuilder builder = new StringBuilder();
		int c = 0;  //当前字符
		int c1 = 0; //前一个字符
		while (true) {
			c = buffer.read();
			if (c == -1) break;
			if (c == '\n' && c1 == '\r') {
				String data1 = builder.substring(0, builder.length() - 1);
				if (data1.charAt(0) == '\\' && data1.charAt(1) == 'n') {
					builder = new StringBuilder();//每次遇到换行就清空
					trees.add(new TreeNode(new CharFreq('\n', Integer.parseInt(data1.substring(3, data1.length())))));
				} else if (data1.charAt(0) == '\\' && data1.charAt(1) == 'r') {
					builder = new StringBuilder();
					trees.add(new TreeNode(new CharFreq('\r', Integer.parseInt(data1.substring(3, data1.length())))));
				} else {
					System.out.println(data1 + " " + data1.charAt(0) + data1.substring(2, data1.length()));
					builder = new StringBuilder();
					trees.add(new TreeNode(new CharFreq(data1.charAt(0), Integer.parseInt(data1.substring(2, data1.length())))));
				}
			} else {
				builder = builder.append((char) c);
			}
			c1 = c;
		}
		// IMPLEMENT THIS METHOD
		return trees;
	}

	/* This TextZip application should support the following command line flags:
	QUESTION 2 PART 1
	=================
		 -a : this uses a default prefix code tree and its compressed
		      file, "a.txz", and decompresses the file, storing the output
		      in the text file, "a.txt".  It should also print out the size
		      of the compressed file (in bytes), the size of the decompressed
		      file (in bytes) and the compression ratio
	QUESTION 2 PART 2
	=================
		 -f : given a text file (args[1]) and the name of an output frequency file
		      (args[2]) this should count the character frequencies in the text file
		      and store these in the frequency file (with one character and its
		      frequency per line).  It should then build the huffman tree based on
		      the character frequencies, and then print out the prefix code for each
		      character
	QUESTION 2 PART 3
	=================
		 -c : given a text file (args[1]) and the name of an output frequency file
		      (args[2]) and the name of the output compressed file (args[3]), this
		      should compress file
	QUESTION 2 PART 4
	=================
		 -d : given a compressed file (args[1]) and its corresponding frequency file
		      (args[2]) and the name of the output decompressed text file (args[3]),
		      this should decompress the file
	*/

	public static void main(String[] args) throws Exception {

		if (args[0].equals("-a")) {
			BitReader br = new BitReader("a.txz");
			FileWriter fw = new FileWriter("a.txt");

			// Get the default prefix code tree
			TreeNode tn = abracadbraTree();
			// Decompress the default file "a.txz"
			decompress(br, tn, fw);      //解压缩

			// Close the ouput file
			fw.close();
			File f1 = new File("a.txz");
			File f2 = new File("a.txt");
			System.out.println("a.txz decompressed by " + ID);
			System.out.println("Size of the compressed file: " + f1.length());
			System.out.println("Size of the original file: " + f2.length());
			System.out.println("压缩比为：" + (double) f1.length() / f2.length() * 100 + "%");
			// Output the compression ratio   输出压缩比
			// Write your own implementation here.
		} else if (args[0].equals("-f")) {  //-f file.txt file.freq
			value_table.clear();
			FileReader fr = new FileReader(args[1]);
			PrintWriter pw = new PrintWriter(new FileWriter(args[2]));

			// Calculate the frequencies
			ArrayList trees = countFrequencies(fr, pw);  //计算数据次数
			// Close the files
			fr.close();
			pw.close();

			// Build the huffman tree
			TreeNode n = buildTree(trees);
			System.out.println(args[1] + "prefix codes by " + ID);
			// Display the codes
			traverse(n, "");
		} else if (args[0].equals("-c")) { //-c file.txt file.freq file.txz
			value_table.clear();
			FileReader fr = new FileReader(args[1]);
			PrintWriter pw = new PrintWriter(new FileWriter(args[2]));
			ArrayList<TreeNode> trees = countFrequencies(fr, pw);
			fr.close();
			pw.close();
			TreeNode n = buildTree(trees);

			// IMPLEMENT NEXT
			// Finish the compress function here

			FileReader fr1 = new FileReader(args[1]);
			BitWriter bw = new BitWriter(args[3]);
			compress(fr1, n, bw);
			bw.close();
			File f1 = new File("file.txz");
			File f2 = new File("file.txt");
			System.out.println(f2.getName() + " compressed by " + ID);
			System.out.println(f1.getName() + "的大小为：" + f1.length() + "bytes");
			System.out.println(f2.getName() + "的大小为：" + f2.length() + "bytes");
			System.out.println("压缩率为" + (double) (f1.length()) / ((double) (f2.length())) * 100 + "%");
			// then output the compression ratio
			// then output the compression ratio
			// Write your own implementation here.
		} else if (args[0].equals("-d")) {
			value_table.clear();
			ArrayList a = readFrequencies(args[2]);    //现在解压   -d file.txz file.freq file.txt
			TreeNode tn = buildTree(a);
			BitReader br = new BitReader(args[1]);
			FileWriter fw = new FileWriter(args[3]);
			decompress(br, tn, fw);
			fw.close();
			File f1 = new File(args[1]);
			File f2 = new File(args[3]);
			System.out.println(f1.getName() + " decompressed by " + ID);
			System.out.println(args[1] + "的大小为：" + f1.length() + "bytes");
			System.out.println(args[3] + "的大小为：" + f2.length() + "bytes");
			System.out.println("压缩率为" + (double) (f1.length()) / ((double) (f2.length())) * 100 + "%");
			// Output the compression ratio
			// Write your own implementation here.


		}
	}
}