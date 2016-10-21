package xyz.husten.finstergram.ui.findstergramdetail;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import xyz.husten.finstergram.FinstergramApp;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.utils.FullScreenImageActivity;


public class FinstergramDetailFragment extends Fragment implements FinstergramDetailContract.View {

  private FinstergramDetailContract.Presenter presenter;
  @Inject Picasso picasso;

  @BindView(R.id.username) TextView username;
  @BindView(R.id.likes) TextView likes;
  @BindView(R.id.comments) TextView comments;
  @BindView(R.id.image) ImageView imageView;
  @BindView(R.id.mapview) MapView mapView;
  private GoogleMap map;

  public static FinstergramDetailFragment newInstance() {
    return new FinstergramDetailFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getActivity()!=null) {
      ((FinstergramApp)getActivity().getApplication()).getNetComponent().inject(this);
    }
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_finstergram_detail, container, false);
    ButterKnife.bind(this, view);
    // Gets the MapView from the XML layout and creates it
    mapView.onCreate(savedInstanceState);
    final MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style);

    // Gets to GoogleMap from the MapView and does initialization stuff
     mapView.getMapAsync(new OnMapReadyCallback() {
      @Override public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.setMapStyle(mapStyleOptions);
      }
    });


    // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
    MapsInitializer.initialize(this.getActivity());


    // Updates the location and zoom of the MapView
    return view;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menuDirections:
        presenter.directions();
        return true;
      case R.id.menuMap:
        presenter.map();
        return true;
      case R.id.menuShare:
        presenter.share();
        return true;
      default:
        return false;
    }
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.start();
    mapView.onResume();
   }

  @Override
  public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }
  @OnClick(R.id.image)
  public void onImageClick() {
    presenter.imageClicked();
  }

  @Override public void setPresenter(FinstergramDetailContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override public void showResult(Result result) {
    username.setText(result.user.username);
    picasso.load(result.images.standardResolution.url).fit().into(imageView);
    likes.setText(getString(R.string.likes, result.likes.count));
    comments.setText(getString(R.string.comments, result.comments.count));
    if(getActivity() != null) {
      ((FinstergramDetailActivity)getActivity()).getSupportActionBar().setTitle(result.location.name);
    }

    if(map != null) {
      LatLng position = new LatLng(result.location.latitude, result.location.longitude);
      CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 14);
      map.animateCamera(cameraUpdate);
      map.addMarker(new MarkerOptions()
          .position(position)
          .title(result.location.name));
    }
  }

  @Override public void openImage(String url) {
    Intent intent = new Intent(getContext(), FullScreenImageActivity.class);
    intent.putExtra(FullScreenImageActivity.EXTRA_IMAGE_URL, url);
    startActivity(intent);
  }

  @Override public void showDirectionUri(String googleMapUri) {
    Uri gmmIntentUri = Uri.parse(googleMapUri);
    openGoogleMaps(gmmIntentUri);
  }

  @Override public void showMapUri(String googleMapUri) {
    Uri gmmIntentUri = Uri.parse(googleMapUri);
    openGoogleMaps(gmmIntentUri);
  }

  @Override public void shareLink(String link) {
    Intent share = new Intent(android.content.Intent.ACTION_SEND);
    share.setType("text/plain");
    share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

    share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
    share.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message, link));

    startActivity(Intent.createChooser(share, getString(R.string.share_chooser_title)));
  }

  private void openGoogleMaps(Uri uri) {
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
    mapIntent.setPackage("com.google.android.apps.maps");
    startActivity(mapIntent);
  }
}
