package com.xiaoneiit.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaoneiit.db.HijasonApplication;
import com.xiaoneiit.definition.Avator;
import com.xiaoneiit.definition.Country;
import com.xiaoneiit.definition.Hijason;
import com.xiaoneiit.definition.Message;
import com.xiaoneiit.definition.Name;
import com.xiaoneiit.definition.Popo;
import com.xiaoneiit.entity.Hotballon;
import com.xiaoneiit.hotballon.R;
import com.xiaoneiit.util.HijasonUtil;

/**
 * 
 * @weibo 我看我还是玩儿蛋去吧
 * @zhihu 狗蛋哥
 * @Pet Cubee (  ˃᷄˶˶̫˶˂᷅  )
 * @OSC Hijason
 * @author Hijason
 * @Github HiCubee
 * #https://github.com/HiCubee
 */
public class HotballonScrollView extends ScrollView{
	
	
	private int riseSpeed;
	private int userNameTextSize = 14;
	private int messageTextSize = 12;
	private int messageLines = 3;
	
	/**
	 * load hotballons
	 */
	List<Hotballon> hotballons;
	
	/**
	 * pictures(popo) of pixels
	 */
	public static final int POPO_WIDTH = 220;
	public static final int POPO_HEIGHT = 243;
	
	/**
	 * first initialization
	 */
	private boolean loadable = true;
	
	/**
	 * the length of each page
	 */
	public static final int PAGE_COUNT = 6;
	
	/**
	 * current page count
	 */
	private int currentPage;

	/**
	 * width of each column
	 */
	private int columnWidth;
	
	/**
	 * to calculate the higher side of layout
	 */
	private int leftColumnHeight;
	private int rightColumnHeight;
	
	private LinearLayout parentLayout;
	private LinearLayout leftLayout;
	private LinearLayout rightLayout;
	
	OnScrollToBottomListener onScrollToBottomListener;
	OnViewClickListener onViewClickListener;

	long loadingTime = 0;
	
	String tag = this.getClass().getSimpleName();

	public HotballonScrollView(Context context) {
		super(context);
	}

	public HotballonScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(!changed && !loadable) return;
//		init();
	}
	
	public void init(){
		initParams();
		resetColumWidth();
		initializeParentLayout();
		initializeLayouts();
		addLayouts2Parent();
	}
	
	private void addLayouts2Parent(){
		parentLayout.addView(leftLayout);
		parentLayout.addView(rightLayout);
		addView(parentLayout);
		invalidate();
	}
	
	private void initializeParentLayout(){
		parentLayout = new LinearLayout(getContext());
		parentLayout.setGravity(Gravity.CENTER);
		parentLayout.setOrientation(LinearLayout.HORIZONTAL);
	}
	
	public void onRefresh(List<Hotballon> hotballons){
		this.hotballons.clear();
		this.hotballons.addAll(hotballons);
		loadImages();
	}
	
	public void onLoadmore(List<Hotballon> hotballons){
		this.hotballons.addAll(hotballons);
		loadImages();
	}
	
	/**
	 * loading after datas ready
	 */
	private void loadImages(){
		int startIndex = currentPage * PAGE_COUNT;
		int endIndex = (currentPage + 1) * PAGE_COUNT;
		endIndex = endIndex >= hotballons.size() ? hotballons.size() : endIndex;
		for(int i = startIndex; i<endIndex; i++){
			loadImage(i);
		}
		currentPage++;
	}
	
	/**
	 * add hotballons views with random size
	 */
	private void loadImage(int index){
		RelativeLayout relativeLayout = new RelativeLayout(getContext());
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayout.setLayoutParams(rl);
		relativeLayout.setGravity(Gravity.CENTER);
		
		/**add popo*/
		ImageView popo = new ImageView(getContext());
		/**
		 * make sure the popo has suitable size
		 */
		int popoWidth = new Random().nextInt((int) (columnWidth * 0.8));
		popoWidth = (int) (popoWidth < columnWidth * 0.411 ? columnWidth * 0.5 + new Random().nextInt((int) (columnWidth * 0.45)) : popoWidth);
		double ratio = (double)POPO_WIDTH / POPO_HEIGHT;
		int popoHeight = (int) (popoWidth / ratio);
		RelativeLayout.LayoutParams popoParams = new RelativeLayout.LayoutParams(popoWidth, popoHeight);
		
		int minMargin = new Random().nextBoolean() ? getDimensionPixelSize(R.dimen.popo_margin_min) : getDimensionPixelSize(R.dimen.popo_margin_min2);
		int maxMargin = new Random().nextBoolean() ? getDimensionPixelSize(R.dimen.popo_margin_max) : getDimensionPixelSize(R.dimen.popo_margin_max2);
		int marginTop = new Random().nextInt(minMargin) + (maxMargin - minMargin);
		popoParams.setMargins(0, marginTop, 0, 0);
		popoParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		popo.setLayoutParams(popoParams);
		popo.setScaleType(ScaleType.FIT_XY);
		popo.setId(Hijason.R.id.HOME_SCROLLVIEW_POPO);
		setImageResource(popo, Popo.getPopo(hotballons.get(index).getPopo()));
		popo.setOnClickListener(new ViewClick(popo, index));
		relativeLayout.addView(popo);
		
		/**add user avator*/
		RoundImageView avator = new RoundImageView(getContext());
		/**
		 * avator's width is one-thirds of popo's
		 */
		int avatorWidth = (int) ((1 / (double)3 ) * popoWidth);
		int avatorHeight = avatorWidth;
		RelativeLayout.LayoutParams avatorParams = new RelativeLayout.LayoutParams(avatorWidth, avatorHeight);
		avatorParams.addRule(RelativeLayout.BELOW, popo.getId());
		avatorParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		avator.setLayoutParams(avatorParams);
		avator.setId(Hijason.R.id.HOME_SCROLLVIEW_AVATOR);
		getImageLoader().displayImage(Avator.getAvator(hotballons.get(index).getAvator()), avator);
		avator.setOnClickListener(new ViewClick(avator, index));
		relativeLayout.addView(avator);
		
		/**add user name*/
		TextView name = new TextView(getContext());
		RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		nameParams.addRule(RelativeLayout.ALIGN_BOTTOM, popo.getId());
		nameParams.addRule(RelativeLayout.RIGHT_OF, avator.getId());
		nameParams.setMargins(getDimensionPixelSize(R.dimen.name_margin_min), 0, 0, 0);
		name.setLayoutParams(nameParams);
		name.setText(Name.getName(hotballons.get(index).getName()));
		name.setTextColor(getColor(R.color.color_white));
		name.setTextSize(userNameTextSize);
		name.setSingleLine(true);
		name.setId(Hijason.R.id.HOME_SCROLLVIEW_NAME);
		name.setOnClickListener(new ViewClick(name, index));
		relativeLayout.addView(name);
		
		/**add user country*/
		ImageView country = new ImageView(getContext());
		RelativeLayout.LayoutParams countryParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		countryParams.addRule(RelativeLayout.BELOW, name.getId());
		countryParams.setMargins(getDimensionPixelSize(R.dimen.name_margin_min), getDimensionPixelSize(R.dimen.name_margin_min), 0, 0);
		countryParams.addRule(RelativeLayout.RIGHT_OF, avator.getId());
		countryParams.width = getDimensionPixelSize(R.dimen.country_width);
		countryParams.height = getDimensionPixelSize(R.dimen.country_height);
		country.setLayoutParams(countryParams);
		country.setImageBitmap(Country.getCountry(getContext(), index));
		country.setId(Hijason.R.id.HOME_SCROLLVIEW_COUNTRY);
		country.setOnClickListener(new ViewClick(country, index));
		relativeLayout.addView(country);
		
		/**add message*/
		TextView message = new TextView(getContext());
		RelativeLayout.LayoutParams messageParams =new RelativeLayout.LayoutParams((int) (popoWidth * 0.8), RelativeLayout.LayoutParams.WRAP_CONTENT);
		messageParams.addRule(RelativeLayout.ALIGN_TOP, popo.getId());
		messageParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		message.setLayoutParams(messageParams);
		message.setGravity(Gravity.CENTER);
		message.setTextSize(messageTextSize);
		message.setTextColor(getColor(R.color.color_white));
		message.setText(Message.getMessage(hotballons.get(index).getMessage()));
		messageParams.setMargins(0, (int) (popoHeight * 0.17),  0, 0);
		message.setLines(messageLines);
		relativeLayout.addView(message);

		getMinHeightLayout(popoHeight + avatorHeight + marginTop).addView(relativeLayout);
	}
	
	/**
	 * @param itemHeight
	 * @return min height one
	 */
	private LinearLayout getMinHeightLayout(int itemHeight){
		if(leftColumnHeight < rightColumnHeight){
			/**
			 * update layout height
			 */
			leftColumnHeight += itemHeight;
			return leftLayout;			
		}else {
			/**
			 * update layout height
			 */
			rightColumnHeight += itemHeight;
			return rightLayout;
		}
	}
	
	private void setImageResource(ImageView imageView, int resId){
		imageView.setImageResource(resId);
	}
	
	private void initParams(){
		setRiseSpeed(25);
		setUserNameTextSize(14);
		setMessageTextSize(12);
		setMessageLines(3);
		setFillViewport(true);
		setId(Hijason.R.id.HOME_SCROLLVIEW);
		hotballons = new ArrayList<Hotballon>();
		loadable = false;
	}
	
	private void resetColumWidth(){
		int screenWidth = HijasonUtil.getWidth(getContext());
		columnWidth = screenWidth / 2;
	}
	
	private void initializeLayouts(){
		leftLayout = new LinearLayout(getContext());
		LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(columnWidth, LinearLayout.LayoutParams.MATCH_PARENT);
		leftParams.weight = 1;
		leftParams.gravity = Gravity.CENTER;
		leftLayout.setLayoutParams(leftParams);
		leftLayout.setOrientation(LinearLayout.VERTICAL);
		
		rightLayout = new LinearLayout(getContext());
		LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(columnWidth, LinearLayout.LayoutParams.MATCH_PARENT);
		rightParams.weight = 1;
		rightParams.gravity = Gravity.CENTER;
		rightLayout.setLayoutParams(rightParams);
		rightLayout.setOrientation(LinearLayout.VERTICAL);
		rightLayout.setGravity(Gravity.CENTER_HORIZONTAL);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		/**
		 * ready to load more
		 * @notice prevent multiple load, loading next time can't less than one secend
		 */
		if(t + getMeasuredHeight() >= getMaxHeight() && loadingTime + 1000 < System.currentTimeMillis()){
			showToast("load more..");
			loadingTime = System.currentTimeMillis();
			onScrollToBottomListener.onScrollToBottom();
		}
	}
	
	public interface OnViewClickListener{
		public void onViewClick(View view, int position);
	}
	
	public interface OnScrollToBottomListener{
		public void onScrollToBottom();
	}
	
	class ViewClick implements OnClickListener{
		
		View view;
		int position;
		public ViewClick(View view, int position){
			this.view = view;
			this.position = position;
		}
		@Override
		public void onClick(View view) {
			onViewClickListener.onViewClick(view, position);
		}
	}
	
	public void setOnViewClickListener(OnViewClickListener onViewClickListener){
		this.onViewClickListener = onViewClickListener;
	}
	
	public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener){
		this.onScrollToBottomListener = onScrollToBottomListener;
	}
	
	public void showToast(String msg){
		Toast.makeText(getContext(), msg, 0).show();
	}
	
	public int getMaxHeight(){
		return leftColumnHeight > rightColumnHeight ? leftColumnHeight : rightColumnHeight;
	}
	
	public HijasonApplication getApp(){
		return (HijasonApplication)getContext().getApplicationContext();
	}
	
	public ImageLoader getImageLoader(){
		return getApp().getImageLoader();
	}
	
	public int getColor(int colorId){
		return getResources().getColor(colorId);
	}
	
	public int getDimensionPixelSize(int dimenId){
		return getResources().getDimensionPixelSize(dimenId);
	}
	
	public Resources getResources(){
		return getContext().getResources();
	}
	
	public void setHotballons(List<Hotballon> hotballons){
		this.hotballons = hotballons;
	}
	
	public void log(String msg){
		Log.d(tag, msg);
	}
	
	public List<Hotballon> getHotballons(){
		return hotballons;
	}

	public int getUserNameTextSize() {
		return userNameTextSize;
	}

	public void setUserNameTextSize(int userNameTextSize) {
		this.userNameTextSize = userNameTextSize;
	}

	public int getMessageTextSize() {
		return messageTextSize;
	}

	public void setMessageTextSize(int messageTextSize) {
		this.messageTextSize = messageTextSize;
	}

	public int getMessageLines() {
		return messageLines;
	}

	public void setMessageLines(int messageLines) {
		this.messageLines = messageLines;
	}

	public int getRiseSpeed() {
		return riseSpeed;
	}

	public void setRiseSpeed(int riseSpeed) {
		this.riseSpeed = riseSpeed;
	}
	
	
	
}