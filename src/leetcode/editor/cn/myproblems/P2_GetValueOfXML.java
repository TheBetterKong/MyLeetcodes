/******************************* Java：获取 XML 里某个元素的值 *******************************/
// 写一个函数，从一个简单的 XML 字符串里提取到某个元素的值
//
//
// 示例 1：
//
// 输入：xml = "<people><name>shopee</name><age>12</age></people>",  path = "people.name"
// 输出：shopee

/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn.myproblems;

public class P2_GetValueOfXML {
    public static void main(String[] args) {
        String xml = "<people><name>shopee</name><age>12</age></people>";
        String path = "age";
        String answer = new P2_GetValueOfXML().etValueOfXML(xml, path);
        System.out.println(answer);
    }

    public String etValueOfXML(String xml, String path) {
        String[] paths = path.split("\\.");

        for (String s : paths) {
            int left = xml.indexOf("<" + s + ">") + s.length() + 2;
            int right = xml.indexOf("</" + s + ">");
            xml = xml.substring(left, right);
        }
        return xml;
    }
}
