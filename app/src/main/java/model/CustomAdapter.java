package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ching.calculationapp.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<MathAnsweredQuestion> {

    private Context context;
    int resource;


    public CustomAdapter(Context context, int resource, List<MathAnsweredQuestion> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        String question = getItem(position).getQuestionText();
        String answer = String.valueOf(getItem(position).getUserAnswer());
        String elapsetime = String.valueOf(getItem(position).getElapsedTime());
        if(elapsetime.equals("10") && answer.equals("")){
            elapsetime = "";
        }
        String status;

        if(getItem(position).getStatus()){
            status = "Correct";
        } else {
            status = "Fail";
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent,false);

        TextView textView_question = (TextView) convertView.findViewById(R.id.textView_listQuestion);
        TextView textView_answer = (TextView) convertView.findViewById(R.id.textView_listAnswer);
        TextView textView_time = (TextView) convertView.findViewById(R.id.textView_listTime);
        TextView textView_status = (TextView) convertView.findViewById(R.id.textView_listStatus);

        textView_question.setText(question);
        textView_answer.setText(answer);
        textView_time.setText(elapsetime);
        textView_status.setText(status);

        return convertView;




    }
}
