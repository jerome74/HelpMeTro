package it.wlp.android.map;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.graphics.Point;
import android.graphics.drawable.Drawable;


public class SitesOverlay extends ItemizedOverlay<OverlayItem>{

	public SitesOverlay(Drawable pDefaultMarker, ResourceProxy pResourceProxy) {
		super(pDefaultMarker, pResourceProxy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onSnapToItem(int arg0, int arg1, Point arg2, IMapView arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected OverlayItem createItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
//{
//	
//	private GoogleMap map;
//	
//	public SitesOverlay(Drawable marker, double lat, double lon, String item ,GoogleMap mMap) 
//	{
//		super(marker);
//		items.add(new OverlayItem(getPoint(lat,
//				lon),
//				item, "my location"));
//		
//		populate();
//	}
//
//	private List<OverlayItem> items=new ArrayList<OverlayItem>();
//    private Drawable marker=null;
//    private OverlayItem inDrag=null;
//    private ImageView dragImage=null;
//    private int xDragImageOffset=0;
//    private int yDragImageOffset=0;
//    private int xDragTouchOffset=0;
//    private int yDragTouchOffset=0;
//
//	@Override
//	protected OverlayItem createItem(int arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//    public void draw(Canvas canvas, MapView mapView,
//                      boolean shadow) {
//      super.draw(canvas, mapView, shadow);
//      
//      boundCenterBottom(marker);
//    }
//	
//	@Override
//	public int size() {
//		return(items.size());
//	}
//	
//	 private GeoPoint getPoint(double lat, double lon) {
//		    return(new GeoPoint((int)(lat*1000000.0),
//		                          (int)(lon*1000000.0)));
//		  }
//	 
//	  @Override
//	    public boolean onTouchEvent(MotionEvent event, MapView mapView) {
//	      final int action=event.getAction();
//	      final int x=(int)event.getX();
//	      final int y=(int)event.getY();
//	      boolean result=false;
//	      
//	      if (action==MotionEvent.ACTION_DOWN) {
//	        for (OverlayItem item : items) 
//	        {  
//	          Point p= map.getProjection().toScreenLocation(new LatLng(item.getPoint().getLatitudeE6() / 1E6
//	        		  																							, item.getPoint().getLongitudeE6() / 1E6));
//	          
//	          if (hitTest(item, marker, x-p.x, y-p.y)) {
//	            result=true;
//	            inDrag=item;
//	            items.remove(inDrag);
//	            populate();
//
//	            xDragTouchOffset=0;
//	            yDragTouchOffset=0;
//	            
//	            setDragImagePosition(p.x, p.y);
//	            dragImage.setVisibility(View.VISIBLE);
//
//	            xDragTouchOffset=x-p.x;
//	            yDragTouchOffset=y-p.y;
//	            
//	            break;
//	          }
//	        }
//	      }
//	      else if (action==MotionEvent.ACTION_MOVE && inDrag!=null) 
//	      {
//	        setDragImagePosition(x, y);
//	        result=true;
//	      }
//	      else if (action==MotionEvent.ACTION_UP && inDrag!=null) 
//	      {
//	        dragImage.setVisibility(View.GONE);
//	        
//	        GeoPoint pt = new GeoPoint((int)((x-xDragTouchOffset) * 1E6) , (int)((y-yDragTouchOffset) * 1E6));
//	        
//	        OverlayItem toDrop=new OverlayItem(pt, inDrag.getTitle(),
//	                                           inDrag.getSnippet());
//	        
//	        items.add(toDrop);
//	        populate();
//	        
//	        inDrag=null;
//	        result=true;
//	      }
//	      
//	      return(result || super.onTouchEvent(event, mapView));
//	    }
//	    
//	    private void setDragImagePosition(int x, int y) {
//	      RelativeLayout.LayoutParams lp=
//	        (RelativeLayout.LayoutParams)dragImage.getLayoutParams();
//	            
//	      lp.setMargins(x-xDragImageOffset-xDragTouchOffset,
//	                      y-yDragImageOffset-yDragTouchOffset, 0, 0);
//	      dragImage.setLayoutParams(lp);
//	    }
//
//}
