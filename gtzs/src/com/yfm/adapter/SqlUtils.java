package com.yfm.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlUtils extends SQLiteOpenHelper {

	private static String name = "gt";

	public SqlUtils(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String goodstype = "create table goodstype(cgoodstype varchar(50),iindex int)";
		String goodsitem = "create table goodsitem(cgoodsname varchar(50),cgoodstype varchar(50),iindex int)";
		db.execSQL(goodstype);
		db.execSQL(goodsitem);
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('°å²Ä',4);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('²»Ðâ¸Ö',8);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('¸Ö°å¾í',3);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('¸Ö²Ä',2);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('¸Ö¹Ü',1);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('Â¯ÁÏ',9);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('ÉúÌú',10);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('ÌØ¸Ö',7);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('ÐÍ¸Ö',6);");
		db.execSQL("insert into goodstype(cgoodstype,iindex) values('ÖÐºñ°å',5);");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÀäÔþ°å','°å²Ä','38');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÈÈÔþ°å','°å²Ä','39');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÈÝÆ÷°å','°å²Ä','40');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ºÏ½ð°å','°å²Ä','41');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸ßÇ¿°å','°å²Ä','42');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÄÍ¸¯Ê´°å','°å²Ä','43');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Âí¿ÚÌú»ù°å','°å²Ä','44');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÎý°å','°å²Ä','45');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÂÁ°å','°å²Ä','46');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹è¸Ö','°å²Ä','47');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÐ¿°å','°å²Ä','48');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²ÊÍ¿°å','°å²Ä','49');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹ÜÏß¸Ö','°å²Ä','50');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values(' ²»Ðâ°å¾í','²»Ðâ¸Ö','96');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸Ö¹Ü','²»Ðâ¸Ö','97');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸ÖÏß²Ä','²»Ðâ¸Ö','98');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸Ö´ø','²»Ðâ¸Ö','99');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸ÖÔ²¸Ö','²»Ðâ¸Ö','100');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸Ö½Ç¸Ö','²»Ðâ¸Ö','101');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸Ö²Û¸Ö','²»Ðâ¸Ö','102');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸Ö±â¸Ö','²»Ðâ¸Ö','103');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ¸ÖÅ÷','²»Ðâ¸Ö','104');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÓÅº¸Ïß','²»Ðâ¸Ö','105');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('À­Ë¿','²»Ðâ¸Ö','106');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ó²Ïß','²»Ðâ¸Ö','107');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values(' ÄÍÈÈ¸Ö','²»Ðâ¸Ö','108');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸ÖË¿','²»Ðâ¸Ö','109');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('´ø¸Ö','¸Ö°å¾í','26');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÈÈ°å¾í','¸Ö°å¾í','27');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Àä°å¾í','¸Ö°å¾í','28');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»Ðâ°å¾í','¸Ö°å¾í','29');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÐ¿°å¾í','¸Ö°å¾í','30');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²ÊÍ¿°å¾í','¸Ö°å¾í','31');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('»¨ÎÆ°å¾í','¸Ö°å¾í','32');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('µÍºÏ½ð°å¾í','¸Ö°å¾í','33');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÎý°å¾í','¸Ö°å¾í','34');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÂÁÐ¿°å¾í','¸Ö°å¾í','35');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ËáÏ´°å¾í','¸Ö°å¾í','36');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÔþÓ²¾í','¸Ö°å¾í','37');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÂÝÎÆ¸Ö','¸Ö²Ä','16');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÆÕÏß','¸Ö²Ä','17');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸ßÏß','¸Ö²Ä','18');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ô²¸Ö','¸Ö²Ä','19');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ïß²Ä','¸Ö²Ä','20');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶þ¼¶ÂÝÎÆ¸Ö','¸Ö²Ä','21');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Èý¼¶ÂÝÎÆ¸Ö','¸Ö²Ä','22');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÅÌÂÝ','¸Ö²Ä','23');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÓÅÏß','¸Ö²Ä','24');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('´øÀß¸Ö','¸Ö²Ä','25');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÎÞ·ì¹Ü','¸Ö¹Ü','1');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¶ÆÐ¿¹Ü','¸Ö¹Ü','2');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('·½¹Ü','¸Ö¹Ü','3');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÂÝÐý¹Ü','¸Ö¹Ü','4');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('º¸¹Ü','¸Ö¹Ü','5');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ö±·ì¹Ü','¸Ö¹Ü','6');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ô²¹Ü','¸Ö¹Ü','7');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹øÂ¯¹Ü','¸Ö¹Ü','8');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÇòÄ«¹Ü','¸Ö¹Ü','9');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¾ØÐÍ¹Ü','¸Ö¹Ü','10');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('»¯·ÊÓÃ¹Ü','¸Ö¹Ü','11');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('½á¹¹¹Ü','¸Ö¹Ü','12');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÖýÌú¹Ü','¸Ö¹Ü','14');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('½ÅÊÖ¼Ü','¸Ö¹Ü','15');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Àä°ÎÎÞ·ì¹Ü','¸Ö¹Ü','147');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¿óÊ¯','Â¯ÁÏ','110');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ãº½¹','Â¯ÁÏ','111');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¾«Ìú·Û','Â¯ÁÏ','112');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('·Ï¸Ö','Â¯ÁÏ','113');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('½¹Ì¿','Â¯ÁÏ','114');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÃºÌ¿','Â¯ÁÏ','115');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÄÍ»ð²ÄÁÏ','Â¯ÁÏ','116');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ì¼ËØ²ÄÁÏ','Â¯ÁÏ','117');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÇòÄ«ÖýÌú','Â¯ÁÏ','118');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸Ö¶§','Â¯ÁÏ','119');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹èÃÌ','Â¯ÁÏ','120');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('·½Å÷','Â¯ÁÏ','121');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹ÜÅ÷','Â¯ÁÏ','122');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸ÖÅ÷','Â¯ÁÏ','123');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('°åÅ÷','Â¯ÁÏ','124');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÇòÄ¥ÉúÌú','Â¯ÁÏ','125');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸¨ÁÏ¸±²úÆ·','Â¯ÁÏ','126');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹èÌú','ÉúÌú','127');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸õÌú','ÉúÌú','128');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('îâÌú','ÉúÌú','129');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('·°Ìú','ÉúÌú','130');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÎÙÌú','ÉúÌú','131');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('îêÌú','ÉúÌú','132');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('îÑÌú','ÉúÌú','133');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÃÌÌú','ÉúÌú','134');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Äø¿ó','ÉúÌú','135');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÓÐÉ«','ÉúÌú','136');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Í­','ÉúÌú','137');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÂÁ','ÉúÌú','138');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ð¿','ÉúÌú','139');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('½ð','ÉúÌú','140');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Òø','ÉúÌú','141');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Äø','ÉúÌú','142');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ç¦','ÉúÌú','143');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Îý','ÉúÌú','144');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ï¡ÍÁ','ÉúÌú','145');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹ó½ðÊô ','ÉúÌú','146');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('º¸Ïß','ÌØ¸Ö','78');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ô² ¸Ö','ÌØ¸Ö','79');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Öá³Ð¸Ö','ÌØ¸Ö','80');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ì¼½á¸Ö','ÌØ¸Ö','81');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('µ¯»É¸Ö','ÌØ¸Ö','82');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸ß¹¤¸Ö','ÌØ¸Ö','83');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ä£¾ß¸Ö','ÌØ¸Ö','84');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¸ÖÎÆÏß','ÌØ¸Ö','85');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('³ÝÂÖ¸Ö','ÌØ¸Ö','86');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ºÏ½á¸Ö','ÌØ¸Ö','87');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÄÍ¸¯Ê´¸Ö','ÌØ¸Ö','88');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÄÍÄ¥¸Ö','ÌØ¸Ö','89');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ì¼¹¤¸Ö','ÌØ¸Ö','90');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ì¼ËØ¸Ö','ÌØ¸Ö','91');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÄÍÈÈ¸Ö','ÌØ¸Ö','92');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Àäïæ¸Ö','ÌØ¸Ö','93');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹¤Ä£¸Ö','ÌØ¸Ö','94');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('½á¹¹¸Ö','ÌØ¸Ö','95');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('HÐÍ¸Ö','ÐÍ¸Ö','64');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²Û¸Ö','ÐÍ¸Ö','65');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹¤×Ö¸Ö','ÐÍ¸Ö','66');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('½Ç¸Ö','ÐÍ¸Ö','67');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('±â¸Ö','ÐÍ¸Ö','68');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Çá¹ì','ÐÍ¸Ö','69');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÖØ¹ì','ÐÍ¸Ö','70');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('·½¸Ö','ÐÍ¸Ö','71');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('²»µÈ±ß½Ç¸Ö','ÐÍ¸Ö','72');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÐÍ¸Ö','ÐÍ¸Ö','73');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Áù½Ç¸Ö','ÐÍ¸Ö','74');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('À­¹âÔ²','ÐÍ¸Ö','75');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ì¼Ô²','ÐÍ¸Ö','76');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('µÈ±ß½Ç¸Ö','ÐÍ¸Ö','77');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('´¬°å','ÖÐºñ°å','51');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('´óÁº°å','ÖÐºñ°å','52');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ä£¾ß°å','ÖÐºñ°å','53');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¹øÂ¯°å','ÖÐºñ°å','54');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÒíÔµ°å','ÖÐºñ°å','55');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ËÄÇÐÆ½°å','ÖÐºñ°å','56');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('»¨ÎÆ°å','ÖÐºñ°å','57');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('µÍºÏ½ð°å','ÖÐºñ°å','58');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÃÌ°å','ÖÐºñ°å','59');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('Ì¼½á°å','ÖÐºñ°å','60');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ºÏ½á°å','ÖÐºñ°å','61');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('¿ªÆ½°å','ÖÐºñ°å','62');");
		db.execSQL("insert into goodsitem(cgoodsname,cgoodstype,iindex) values('ÄÍÄ¥°å','ÖÐºñ°å','63');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
