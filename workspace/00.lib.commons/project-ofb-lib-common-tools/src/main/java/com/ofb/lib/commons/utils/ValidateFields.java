package com.ofb.lib.commons.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateFields {
	public static final String p_format_date_short_PTBR	= "dd-MM-yyyy";
	public static final String p_format_date_time_PTBR	= "dd-MM-yyyy HH:mm";
	public static final String p_format_date_full_PTBR	= "dd-MM-yyyy HH:mm:ss";
	public static final String p_format_month_year_PTBR	= "MM-yyyy";
	public static final String p_format_time_short_PTBR	= "HH:mm";
	public static final String p_format_time_full_PTBR	= "HH:mm:ss";	
	public static final String p_format_number_short_PTBR = "9,99";
	public static final String p_format_number_full_PTBR  = "9.999,99";
	
	public static final String p_format_date_short_EN	= "yyyy-MM-dd";
	public static final String p_format_date_time_EN	= "yyyy-MM-dd HH:mm";
	public static final String p_format_date_full_EN	= "yyyy-MM-dd HH:mm:ss";
	public static final String p_format_month_year_EN	= "yyyy-MM";
	public static final String p_format_time_short_EN	= "HH:mm";
	public static final String p_format_time_full_EN	= "HH:mm:ss";		
	public static final String p_format_number_short_EN = "9.99";
	public static final String p_format_number_full_EN  = "9,999.99";
	
	public static final String p_format_date_oracle	= "yyyy-MM-dd HH:mm:ss";
	public static final String p_format_number_oracle  = "9.99";
	
	/* @Valid specificatons for 'C or N' */
	public static final String regExCredOrDeb   = "^(c|C|d|D)?$";
	public static final String descCredOrDeb    = "Cred/Deb information";
	public static final String messageCredOrDeb = "Cred/Deb is invalid. Required is: 'c, C, d or D'";
	public static final String exampleCredOrDeb = "C/D";
	
	/* @Valid specificatons for '0 or 1' */
	public static final String regExZeroOrOne   = "^(0|1)?$";
	public static final String descZeroOrOne    = "0/1 information";
	public static final String messageZeroOrOne = "0/1 is invalid. Required is: '0 or 1'";
	public static final String exampleZeroOrOne = "0/1";
	
	/* @Valid specificatons for 'Yes or No' */
	public static final String regExYesOrNo     = "^(n|N|no|No|NO|y|Y|yes|Yes|YES)?$";
	public static final String descYesOrNo      = "Yes/No information";
	public static final String messageYesOrNo   = "Yes/No is invalid. Required is: 'n, N, no, No or NO' "+
			   									 	 "or 'y, Y, yes, Yes or YES'.";
	public static final String exampleYesOrNo   = "yes/no";
	
	/* @Valid specificatons for 'True or False' */
	public static final String regExTrueOrFalse   = "^(1|t|T|true|True|TRUE|0|f|F|false|False|FALSE)?$";
	public static final String descTrueOrFalse    = "True/False information";
	public static final String messageTrueOrFalse = "True/False is invalid. Required is: 't, true, True or TRUE' "+
													  "or 'f, false, False or FALSE'.";
	public static final String exampleTrueOrFalse = "true/false";

	/* @Valid specificatons for 'Asc or Desc' */
	public static final String regExSortOrder   = "^(no|No|NO|asc|Asc|ASC|desc|Desc|DESC)?$";
	public static final String descSortOrder 	 = "Sorting Indicator";
	public static final String messageSortOrder = "Sorting Order is invalid. Required is: 'no, No or NO', " +
												   "'asc, Asc or ASC', 'desc, Desc or DES'.";
	public static final String exampleSortOrder  = "no/asc/desc";
	
	/* @Valid specificatons for 'Process Status' */
	public static final String regExProcessStatus   = "^(1|running|2|successfully|3|error)?$";
	public static final String descProcessStatus    = "Process Status";
	public static final String messageProcessStatus = "Process Status is invalid. Required is: '1' or 'running', " +
														"'2' or 'successfully', " +
														"'3' or 'error'.";
	public static final String exampleProcessStatus = "Allowed: '1' or " +
														"'running', '2' or 'successfully', "+
														"'3' or 'error'.";
	
	//==================================================================================================================
	public static final String regExZipCode   = "^\\d{2}\\d{3}[-\\s]?\\d{3}?$";
	public static final String descZipCode    = "ZipCode (CEP) Information";
	public static final String messageZipCode = "ZipCode (CEP) is invalid; Required is format '99999999' or '99999-999'";
	public static final String exampleZipCode = "99999-999";	
	
	//==================================================================================================================
	public static final String regExCreditCard   = "^(((\\d{4}[-|\" \"|\\.])|(\\d{4})){3}\\d{4})?$";
	public static final String descCreditCard    = "Credit Card Information";
	public static final String messageCreditCard = "Credit Card is invalid; Required is format '9999-9999-9999-999' or '9999.9999.9999.9999'";
	public static final String exampleCreditCard = "9999.9999.9999.9999";	
			
	//==================================================================================================================
	public static final String regExRG   = "^([0-9]{2,3}\\.?[0-9]{2,3}\\.?[0-9]{3}\\-?[A-Za-z0-9]{1})?$";
	public static final String descRG    = "RG Information";
	public static final String messageRG = "RG Number is invalid; Required is format '99.999.999-9' or '999.999.999-X'";
	public static final String exampleRG = "999.999.999-X";	
	
	//==================================================================================================================
	public static final String regExInscEstadual   = "^(((\\d{3}[-|\" \"|\\.])|(\\d{3})){3}\\d{3})?$";
	public static final String descInscEstadual    = "Credit Card Information";
	public static final String messageInscEstadual = "Credit Card is invalid; Required is format '9999-9999-9999-999' or '9999.9999.9999.9999'";
	public static final String exampleInscEstadual = "9999.9999.9999.9999";
	
	//==================================================================================================================
	public static final String regExCode2Numbers = "^\\d{2}?$";
	public static final String descCode2Numbers    = "Code Numeric Information";
	public static final String messageCode2Numbers = "Code Numeric is invalid; Required is format '99'";
	public static final String exampleCode2Numbers = "99";	
	
	public static final String regExCode3Numbers = "^\\d{3}?$";
	public static final String descCode3Numbers    = "Code Numeric Information";
	public static final String messageCode3Numbers = "Code Numeric is invalid; Required is format '999'";
	public static final String exampleCode3Numbers = "999";	
	
	public static final String regExCode4Numbers = "^\\d{4}?$";
	public static final String descCode4Numbers    = "Code Numeric Information";
	public static final String messageCode4Numbers = "Code Numeric is invalid; Required is format '9999'";
	public static final String exampleCode4Numbers = "9999";	
	
	public static final String regExCode6Numbers = "^\\d{6}?$";
	public static final String descCode6Numbers    = "Code Numeric Information";
	public static final String messageCode6Numbers = "Code Numeric is invalid; Required is format '999999'";
	public static final String exampleCode6Numbers = "999999";	
	
	public static final String regExCode8Numbers = "^\\d{8}?$";
	public static final String descCode8Numbers    = "Code Numeric Information";
	public static final String messageCode8Numbers = "Code Numeric is invalid; Required is format '99999999'";
	public static final String exampleCode8Numbers = "99999999";	
	
	//==================================================================================================================
	/* Phone and Mobile Validate */
	//40711233
	public static final String regExPhone 	= "^\\d{8}?$";
	public static final String descPhone    = "Phone Information";
	public static final String messagePhone = "Phone Number is invalid; Required is format '99999999'";
	public static final String examplePhone = "99999999";	
	
	//940711233
	public static final String regExMobile = "^\\d{9}?$";
	public static final String descMobile    = "Mobile Information";
	public static final String messageMobile = "Mobile Number is invalid; Required is format '999999999'";
	public static final String exampleMobile = "999999999";	
	
	//01140711233
	public static final String regExPhoneWithDDD   = "^\\d{11}?$";
	public static final String descPhoneWithDDD    = "DDD+Phone Information";
	public static final String messagePhoneWithDDD = "DDD+Phone Number is invalid; Required is format '99999999999'";
	public static final String examplePhoneWithDDD = "999999999999";	
	
	//011940711233
	public static final String regExMobileWithDDD 	 = "^\\d{12}?$";
	public static final String descMobileWithDDD     = "DDD+Mobile Information";
	public static final String messageMobileWithDDDD = "DDD+Mobile Number is invalid; Required is format '999999999999'";
	public static final String exampleMobileWithDDD  = "999999999999";
	
	//5501140711233
	public static final String regExPhoneWithIntCodeAndDDD = "^\\d{13}?$";
	public static final String descPhoneWithIntCodeAndDDD    = "International Code+DDD+Phone Information";
	public static final String messagePhoneWithIntCodeAndDDD = "International Code+DDDD+Phone Number is invalid; Required is format '99999999999999'";
	public static final String examplePhoneWithIntCodeAndDDD = "999999999999999";
	
	//55011940711233
	public static final String regExMobileWithIntCodeAndDDD    = "^\\d{14}?$";
	public static final String descMobileWithIntCodeAndDDD     = "International Code+DDD+Mobile Information";
	public static final String messageMobileWithIntCodeAndDDD  = "International Code+DDD+Mobile Number is invalid; Required is format '9999999999999'";
	public static final String exampleMobileWithIntCodeAndDDD  = "9999999999999";

	//4071-1233
	public static final String regExMaskPhone   = "^\\d{4}[- .]?\\d{4}?$";
	public static final String descMaskPhone    = "Phone with Mask Information";
	public static final String messageMaskPhone = "Phone Mask Number is invalid; Required is format '9999-9999'";
	public static final String exampleMaskPhone = "9999-9999";

	//4071-1233
	public static final String regExMaskMobile   = "^\\d{5}[- .]?\\d{4}?$";
	public static final String descMaskMobile    = "Mobile with Mask Information";
	public static final String messageMaskMobile = "Mobile Mask Number is invalid; Required is format '99999-9999'";
	public static final String exampleMaskMobile = "99999-9999";
	
	//011 4071-1233
	public static final String regExMaskPhoneWithDDD   = "^(\\d{3}[- .]?)\\d{4}[- .]?\\d{4}?$";
	public static final String descMaskPhoneWithDDD    = "DDD+Phone with Mask Information";
	public static final String messageMaskPhoneWithDDD = "DDD+Phone Mask Number is invalid; Required is format '999 9999-9999'";
	public static final String exampleMaskPhoneWithDDD = "999 9999-9999";	
	
	//+55 (011) 4701-1233
	public static final String regExMaskPhoneWithIntCodeAndDDD = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{4}[- .]?\\d{4}?$";
	public static final String descMaskPhoneWithIntCodeAndDDD    = "International Code+DDD+Phone with Mask Information";
	public static final String messageMaskPhoneWithIntCodeAndDDD = "International Code+DDD+Phone Mask Number is invalid; Required is format '+99 (999) 9999-9999'";
	public static final String exampleMaskPhoneWithIntCodeAndDDD = "+99 (999) 9999-9999";
	
	//011 94071-1233
	public static final String regExMaskMobileWithDDD    = "^(\\d{3}[- .]?)\\d{5}[- .]?\\d{4}?$";
	public static final String descMaskMobileWithDDD     = "DDD+Mobile with Mask Information";
	public static final String messageMaskMobileWithDDD  = "DDD+Mobile Mask Number is invalid; Required is format '999 99999-9999'";
	public static final String exampleMaskMobileWithDDD  = "999 99999-9999";
	
	//+55 (011) 94701-1233
	public static final String regExMaskMobileWithIntCodeAndDDD   = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{5}[- .]?\\d{4}?$";
	public static final String descMaskMobileWithIntCodeAndDDD    = "International Code+DDD+Mobile with Mask Information";
	public static final String messageMaskMobileWithIntCodeAndDDD = "International Code+DDD+Mobile Mask Number is invalid; Required is format '+99 (999) 99999-9999'";
	public static final String exampleMaskMobileWithIntCodeAndDDD = "+99 (999) 99999-9999";
	
	//==================================================================================================================
	/* @Valid specificatons for Numbers (positives or negatives) 9 int */
	public static final String regExNumberNotDecimals   = "^([-]?(\\d{1,9}))?$";
	public static final String descNumberNotDecimals    = "Numbers Information";
	public static final String messageNumberNotDecimals = "Number is invalid; Required is format '9999999'.";
	public static final String exampleNumberNotDecimals = "9999999999";	
	
	/* @Valid specificatons for Numbers (positives or negatives) with TWO optional decimals */
	public static final String regExNumberWith2Decimals   = "^([-]?\\d{1,9}(\\.\\d{1,2}))?$";
	public static final String desc_Numbers_with_optional_two_Decimals    = "Number/Decimals Information";
	public static final String messageNumberWith2Decimals = "Number/Decimals is invalid. Required is format: '999999' or '999999.99'.";
	public static final String exampleNumberWith2Decimals = "Allowed: '999999999999' (up to 9 positions) or "+
																			"999999999.99";
	
	/* @Valid specificatons for Numbers (positives or negatives) with FOUR optional decimals */
	public static final String regExNumberWith4Decimals   = "^([-]?\\d{1,9}(\\.\\d{1,4}))?$";	
	public static final String desc_Numbers_with_optional_four_Decimals    = "Number/Decimals Information";
	public static final String messageNumberWith4Decimals = "Number/Decimals is invalid. Required is format: '999999' or '999999.9999'.";
	public static final String exampleNumberWith4Decimals = "Allowed: '999999999999' (up to 9 positions) or "+
			 																 "999999999.9999";
		
	/* @Valid specificatons for Numbers (positives or negatives) with SIX optional decimals */
	public static final String regExNumberWith6Decimals   = "^([-]?\\d{1,9}(\\.\\d{1,6}))?$";
	public static final String desc_Numbers_with_optional_six_Decimals    = "Number/Decimals Information";
	public static final String messageNumberWith6Decimals = "Number/Decimals is invalid. Required is format: '999999' or '999999.999999'.";
	public static final String exampleNumberWith6Decimals = "Allowed: '999999999999' (up to 9 positions) or " +
																			 "999999999.999999";
	
	//==================================================================================================================
	/* 
	 * Format , HH:MM, HH:MM:SS
	 * 
	 * @Valid specificatons for Hour/Minutes 'HH:MM' */
	public static final String regExTimeHHMM = "^((([0-1]{0,1}[0-9])|(2[0-3])):[0-5]{0,1}[0-9])?$";
	public static final String descTimeHHMM    = "Hour/Minutes Information";
	public static final String messageTimeHHMM = "Hour/Minutes is invalid. Format required is 'HH:MM'.";
	public static final String exampleTimeHHMM = "HH:MM";
	
	/* @Valid specificatons for Hour/Minutes/Seconds 'HH:MM:SS' */
	public static final String regExTimeHHMMSS   = "^((([0-1]{0,1}[0-9])|(2[0-3])):[0-5]{0,1}[0-9]:[0-5]{0,1}[0-9])?$";
	public static final String descTimeHHMMSS    = "Hour/Minutes/Seconds Information";
	public static final String messageTimeHHMMSS = "Hour/Minutes/Seconds is invalid. Format required is 'HH:MM:SS'.";
	public static final String exampleTimeHHMMSS = "HH:MM:SS";
	
	//==================================================================================================================
	/* 
	 * Format MM YYYY, YYYY MM
	 * 
	 * @Valid specificatons for year/month 'yyyy-mm' */
	public static final String regExYYYYMMdash   = "^(([0-9]{4,4})[-]((0[1-9])|(1[0-2])))?$";
	public static final String descYYYYMMdash    = "Year/Month Information";
	public static final String messageYYYYMMdash = "Year/Month is invalid. Format required is YYYY-MM.";
	public static final String exampleYYYYMMdash = "YYYY-MM";	

	/* @Valid specificatons for year/month 'yyyy/mm' */
	public static final String regExYYYYMMslash   = "^(([0-9]{4,4})[\\/]((0[1-9])|(1[0-2])))?$";
	public static final String descYYYYMMslash    = "Year/Month Information";
	public static final String messageYYYYMMslash = "Year/Month is invalid. Format required is YYYY/MM.";
	public static final String exampleYYYYMMslash = "YYYY/MM";	
	
	/* @Valid specificatons for year/month 'yyyymm' */
	public static final String regExYYYYMM   = "^(([0-9]{4,4})((0[1-9])|(1[0-2])))?$";
	public static final String descYYYYMM    = "Year/Month Information";
	public static final String messageYYYYMM = "Year/Month is invalid. Format required is YYYYMM.";
	public static final String exampleYYYYMM = "YYYYMM";	
	
	/* @Valid specificatons for month/year 'mm-yyyy' */
	public static final String regExMMYYYYdash = "^(((0[1-9])|(1[0-2]))[-]([0-9]{4,4}))?$";
	public static final String descMMYYYYdash    = "Month/Year Information";
	public static final String messageMMYYYYdash = "Month/Year is invalid. Format required is MM-YYYY.";
	public static final String exampleMMYYYYdash = "MM-YYYY";		

	/* @Valid specificatons for month/year 'mm-yyyy' */
	public static final String regExMMYYYYslash = "^(((0[1-9])|(1[0-2]))[\\/]([0-9]{4,4}))?$";
	public static final String descMMYYYYslash    = "Month/Year Information";
	public static final String messageMMYYYYslash = "Month/Year is invalid. Format required is MM/YYYY.";
	public static final String exampleMMYYYYslash = "MM/YYYY";

	/* @Valid specificatons for month/year 'mmyyyy' */
	public static final String regExMMYYYY = "^(((0[1-9])|(1[0-2]))([0-9]{4,4}))?$";
	public static final String descMMYYYY    = "Month/Year Information";
	public static final String messageMMYYYY = "Month/Year is invalid. Format required is MMYYYY.";
	public static final String exampleMMYYYY = "MMYYYY";
	
	//==================================================================================================================
	/* 
	 * Format YYYY MM DD
	 * 
	 * 
	 * @Valid specificatons for date/hour Format: 'yyyy-mm-dd hh:mm:ss' */
	public static final String regExYYYYMMDDhhMMdash   ="^((((\\d\\d)(([02468][048])|([13579][26]))-02-29)|(((\\d\\d)(\\d\\d)))-((((0\\d)|(1[0-2]))-((0\\d)|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))\\s(([01]\\d|2[0-3]):([0-5]\\d)))?$";
	public static final String descYYYYMMDDhhMMdash    = "Date/Hours Information";
	public static final String messageYYYYMMDDhhMMdash = "Date/hour is invalid. Format required is YYYY-MM-DD HH:MM";
	public static final String exampleYYYYMMDDhhMMdash = "YYYY-MM-DD HH:MM";		
	
	/* @Valid specificatons for date/hour  Format: 'yyyy-mm-dd hh:mm:ss' */
	public static final String regExYYYYMMDDhhMMslash   ="^((((\\d\\d)(([02468][048])|([13579][26]))\\/02\\/29)|(((\\d\\d)(\\d\\d)))\\/((((0\\d)|(1[0-2]))\\/((0\\d)|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))\\/31)|(((0[1,3-9])|(1[0-2]))\\/(29|30)))))\\s(([01]\\d|2[0-3]):([0-5]\\d)))?$";
	public static final String descYYYYMMDDhhMMslash    = "Date/Hours Information";
	public static final String messageYYYYMMDDhhMMslash = "Date/hour is invalid. Format required is YYYY/MM/DD HH:MM";
	public static final String exampleYYYYMMDDhhMMslash = "YYYY/MM/DD HH:MM";		

	/* @Valid specificatons for date/hour  Format: 'yyyy-mm-dd hh:mm:ss' */
	public static final String regExYYYYMMDDhhmmSSdash   ="^((((\\d\\d)(([02468][048])|([13579][26]))-02-29)|(((\\d\\d)(\\d\\d)))-((((0\\d)|(1[0-2]))-((0\\d)|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))\\s(([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)))?$";
	public static final String descYYYYMMDDhhmmSSdash    = "Date/Hours Information";
	public static final String messageYYYYMMDDhhmmSSdash = "Date/hour is invalid. Format required is YYYY-MM-DD HH:MM:SS.";
	public static final String exampleYYYYMMDDhhmmSSdash = "YYYY-MM-DD HH:MM:SS";	
	
	/* @Valid specificatons for date/hour  Format: 'yyyy-mm-dd hh:mm:ss' */
	public static final String regExYYYYMMDDhhmmSSslash   = "^((((\\d\\d)(([02468][048])|([13579][26]))\\/02\\/29)|(((\\d\\d)(\\d\\d)))\\/((((0\\d)|(1[0-2]))\\/((0\\d)|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))\\/31)|(((0[1,3-9])|(1[0-2]))\\/(29|30)))))\\s(([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)))?$";
	public static final String descYYYYMMDDhhmmSSslash    = "Date/Hours Information";
	public static final String messageYYYYMMDDhhmmSSslash = "Date/hour is invalid. Format required is YYYY/MM/DD HH:MM:SS";
	public static final String exampleYYYYMMDDhhmmSSslash = "YYYY/MM/DD HH:MM:SS";	
	
	/* @Valid specificatons for date 'dd/mm/yyyy' */
	public static final String regExDDMMYYYYslash   = "^(?:(?:(?:0?[1-9]|1\\d|2[0-8])\\/(?:0?[1-9]|1[0-2]))\\/(?:(?:1[6-9]|[2-9]\\d)\\d{2}))$|^(?:(?:(?:31\\/0?[13578]|1[02])|(?:(?:29|30)\\/(?:0?[1,3-9]|1[0-2])))\\/(?:(?:1[6-9]|[2-9]\\d)\\d{2}))$|^(?:29\\/0?2\\/(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26]))))?$";
	public static final String descDDMMYYYYslash    = "Date Information";
	public static final String messageDDMMYYYYslash = "Date is invalid. Format required is DD/MM/YYYY.";
	public static final String exampleDDMMYYYYslash = "DD/MM/YYYY";

	/* @Valid specificatons for date 'dd-mm-yyyy' */
	public static final String regExDDMMYYYYdash   = "^(?:(?:(?:0?[1-9]|1\\d|2[0-8])-(?:0?[1-9]|1[0-2]))-(?:(?:1[6-9]|[2-9]\\d)\\d{2}))$|^(?:(?:(?:31-0?[13578]|1[02])|(?:(?:29|30)-(?:0?[1,3-9]|1[0-2])))-(?:(?:1[6-9]|[2-9]\\d)\\d{2}))$|^(?:29-0?2-(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26]))))?$";
	public static final String descDDMMYYYYdash    = "Date Information";
	public static final String messageDDMMYYYYdash = "Date is invalid. Format required is DD-MM-YYYY.";
	public static final String exampleDDMMYYYYdash = "DD-MM-YYYY";
	
	/* @Valid specificatons for date 'yyyy/mm/dd' */
	public static final String regExYYYYMMDDslash   = "^(((\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2}|[1-9]\\d{3})(\\/)(((0[13578]|1[02])(\\/)(0[1-9]|[12]\\d|3[01]))|((0[469]|11)(\\/)(0[1-9]|[12]\\d|30))|(02(\\/)(0[1-9]|[1]\\d|2[0-8]))))|(((\\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))(\\/)02(\\/)29))?$";
	public static final String descYYYYMMDDslash    = "Date Information.";
	public static final String messageYYYYMMDDslash = "Date is invalid. Format required is YYYY/MM/DD.";
	public static final String exampleYYYYMMDDslash = "YYYY/MM/DD";	
	
	/* @Valid specificatons for date 'yyyy-mm-dd'" */
	public static final String regExYYYYMMDDdash = "^(((\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2}|[1-9]\\d{3})(-)(((0[13578]|1[02])(-)(0[1-9]|[12]\\d|3[01]))|((0[469]|11)(-)(0[1-9]|[12]\\d|30))|(02(-)(0[1-9]|[1]\\d|2[0-8]))))|(((\\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))(-)02(\\/)29))?$";
	public static final String descYYYYMMDDdash    = "Date Oracle Information";
	public static final String messageYYYYMMDDdash = "Date is invalid. Format required is YYYY-MM-DD.";
	public static final String exampleYYYYMMDDdash = "YYYY-MM-DD";	

	//==================================================================================================================
	/* 
	 * Format DD MM YYYY
	 * @Valid specificatons for date 'dd-mm-yyyy hh:mm:ss' */
	public static final String regExDDMMYYYYhhmmSSdash   = "^((0[1-9]|1\\d|2[0-8]|29(?=-\\d\\d-(?!1[01345789]00|2[1235679]00)\\d\\d(?:[02468][048]|[13579][26]))|30(?!-02)|31(?=-0[13578]|-1[02]))-(0[1-9]|1[0-2])-([12]\\d{3}) ([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d))?$";
	public static final String descDDMMYYYYhhmmSSdash    = "Date/Hours Information";
	public static final String messageDDMMYYYYhhmmSSdash = "Date/hour is invalid. Format required is DD-MM-YYYY HH:MM:SS.";
	public static final String exampleDDMMYYYYhhmmSSdash = "DD-MM-YYYY HH:MM:SS";		
	
	/* @Valid specificatons for date 'dd-mm-yyyy hh:mm' */
	public static final String regExDDMMYYYYhhMMdash   = "^((0[1-9]|1\\d|2[0-8]|29(?=-\\d\\d-(?!1[01345789]00|2[1235679]00)\\d\\d(?:[02468][048]|[13579][26]))|30(?!-02)|31(?=-0[13578]|-1[02]))-(0[1-9]|1[0-2])-([12]\\d{3}) ([01]\\d|2[0-3]):([0-5]\\d))?$";
	public static final String descDDMMYYYYhhMMdash    = "Date/Hours Information";
	public static final String messageDDMMYYYYhhMMdash = "Date/hour is invalid. Format required is DD-MM-YYYY HH:MM";
	public static final String exampleDDMMYYYYhhMMdash = "DD-MM-YYYY HH:MM";		

	
	/* @Valid specificatons for date 'dd/mm/yyyy hh:mm:ss' */
	public static final String regExDDMMYYYYhhmmSSslash   = "^((0[1-9]|1\\d|2[0-8]|29(?=\\/\\d\\d\\/(?!1[01345789]00|2[1235679]00)\\d\\d(?:[02468][048]|[13579][26]))|30(?!-02)|31(?=\\/0[13578]|\\/1[02]))\\/(0[1-9]|1[0-2])\\/([12]\\d{3}) ([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d))?$";
	public static final String descDDMMYYYYhhmmSSslash    = "Date/Hours Information";
	public static final String messageDDMMYYYYhhmmSSslash = "Date/hour is invalid. Format required is DD/MM/YYYY HH:MM:SS";
	public static final String exampleDDMMYYYYhhmmSSslash = "DD/MM/YYYY HH:MM:SS";
	
	/* @Valid specificatons for date 'dd/mm/yyyy hh:mm' */
	public static final String regExDDMMYYYYhhMMslash   = "^((0[1-9]|1\\d|2[0-8]|29(?=\\/\\d\\d\\/(?!1[01345789]00|2[1235679]00)\\d\\d(?:[02468][048]|[13579][26]))|30(?!-02)|31(?=\\/0[13578]|\\/1[02]))\\/(0[1-9]|1[0-2])\\/([12]\\d{3}) ([01]\\d|2[0-3]):([0-5]\\d))?$";
	public static final String descDDMMYYYYhhMMslash    = "Date/Hours Information";
	public static final String messageDDMMYYYYhhMMslash = "Date/hour is invalid. Format required is DD/MM/YYYY HH:MM";
	public static final String exampleDDMMYYYYhhMMslash = "DD/MM/YYYY HH:MM";	

}
