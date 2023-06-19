package getGIF;

public class Maingif {
	public static void main(String[] args) {
//		"lidia","kunimitsu","fahkumram","leroy","ganryu","zafina","geese","noctis","lei",
//		"anna","craig","armorking","julia","gouki","nina","bob","masterraven","eliza","eddy","miguel",
//		"lee","kuma_panda","claudio","katarina","luckychloe","shaheen","josie","gigas","kazumi","kazuya",
//		"heihachi","lars","asuka","lili","alisa","xiaoyu","feng","paul","law","steve","leo","hwoarang","king",
//		"dragunov","bryan","jin","devil_jin","yoshimitsu","jack7"
		String[] character={"jack7"};
		for(int i=0;i<1;i++) {
			long sleepLength = 1500;
			try {
				Thread.sleep(sleepLength);
				new DownloadAndSave("https://www.tekken-official.jp/tk7fr-r2/images/chara-stage/character/"+character[i]+"/img_chara.png",character[i]);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
	
	
}
