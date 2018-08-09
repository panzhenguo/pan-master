package com.pan.common.utils;

import lombok.Data;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Data
public class PinYin4J {
	private String pinyinAll;
	private String pinyinShort;
	 /**
     * 得到指定汉字的拼�?
     * 注意:不应该被频繁调用，它消�?�一定内�?
     * @param hanzi
     * @return
     */
	
	
    public PinYin4J(String chinese){
        StringBuilder pinyin = new StringBuilder();
        StringBuilder pinyinShortsb = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();//控制转换是否大小写，是否带音�?
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //由于不能直接对多个汉字转换，只能对单个汉字转�?
        char[] arr = chinese.toCharArray();
        String[] pinyinArr=null;
        char[] pinyinShort=null;
        for (int i = 0; i < arr.length; i++) {
            if(Character.isWhitespace(arr[i]))continue;//如果是空格，则不处理，进行下次遍�?
            //汉字�?2个字节存储，肯定大于127，所以大�?127就可以当为汉字转�?
            if(arr[i]>127){
                try {
                    //由于多音字的存在，单 dan shan
                	pinyinArr = PinyinHelper.toHanyuPinyinStringArray(arr[i], format);
                    if(pinyinArr!=null){
                    	pinyin.append(pinyinArr[0]);
                    	pinyinShort=pinyinArr[0].toCharArray();
                    	if(pinyinShort!=null) {
                    		pinyinShortsb.append(pinyinShort[0]);
                    	}
                    }else {
                    	// 可能是个无法转换的字�?
                    	pinyin.append(pinyinArr);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    //不是正确的汉�?
                    pinyin.append(arr[i]);
                }
            }else {
                //不是汉字�?
                pinyin.append(arr[i]);
            }
        }
        this.pinyinAll=pinyin.toString();
        this.pinyinShort=pinyinShortsb.toString();
    }
}
