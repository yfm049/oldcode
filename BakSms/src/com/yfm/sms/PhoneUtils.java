package com.yfm.sms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class PhoneUtils {

	public static String getContactNameByPhoneNumber(ContentResolver cr, String address) {
        String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER };
		if(address!=null&&address.startsWith("+86")){
			address=address.substring(3);
		}
        // 将自己添加到 msPeers 中
        Cursor cursor = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, // Which columns to return.
                ContactsContract.CommonDataKinds.Phone.NUMBER + " like '%"
                        + address + "%'", // WHERE clause.
                null, // WHERE clause value substitution
                null); // Sort order.

        if (cursor == null) {
            Log.d("phone", "getPeople null");
            return "";
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            // 取得联系人名字
            int nameFieldColumnIndex = cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String name = cursor.getString(nameFieldColumnIndex);
            return name;
        }
        return "";
    }
}
