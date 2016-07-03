package info.androidhive.materialnavbar.ViewAdapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;

import info.androidhive.materialnavbar.Animations;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {



    // Recycler View Adapter
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        ImageButton FavBut;
        ImageButton ReportBut;
        TextView categoryHeader;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.weetjeTitel);
            personAge = (TextView) itemView.findViewById(R.id.weetjeContent);
            personPhoto = (ImageView) itemView.findViewById(R.id.card_picture);
            FavBut = (ImageButton) itemView.findViewById(R.id.favButton);
            ReportBut = (ImageButton) itemView.findViewById(R.id.ReportBut);
            ReportBut = (ImageButton) itemView.findViewById(R.id.ReportBut);
            categoryHeader = (TextView) itemView.findViewById(R.id.category_name);
          //  UrlImageViewHelper.setUrlDrawable(personPhoto, "http://placehold.it/350x150");
            //urlDownload();

        }
    }

    public void urlDownload(View itemView,String URL){
        ImageView personPhoto = (ImageView) itemView.findViewById(R.id.card_picture);
        UrlImageViewHelper.setUrlDrawable(personPhoto, URL);
    }

    List<CardItem> cardItems;

    public RVAdapter(List<CardItem> cardItems) {
        this.cardItems = cardItems;
    }


    public Object getCard(int position) {
        return cardItems.get(position);
    }

    public List<CardItem> getCardItems() {
        return cardItems;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carditem, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }



    // de 'onCreate'
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        // vult het object/element met
        personViewHolder.categoryHeader.setText(cardItems.get(i).catId);
        personViewHolder.personName.setText(cardItems.get(i).name);
        personViewHolder.personAge.setText(cardItems.get(i).age);
        personViewHolder.personPhoto.setImageResource(cardItems.get(i).photoId);

        //fav
        personViewHolder.FavBut.setImageResource(cardItems.get(i).favId);
        //report
        personViewHolder.ReportBut.setImageResource(cardItems.get(i).reportId);
//

        //zet animation op dit object/element
        Animations.animateScatter(personViewHolder, true);

    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

}

