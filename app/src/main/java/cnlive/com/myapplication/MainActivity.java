package cnlive.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.student.entity.Student;
import com.student.entity.WorkMan;

import java.util.List;

import cnlive.com.myapplication.dbmaneger.CommentUtils;

public class MainActivity extends AppCompatActivity {
    private Button addbutton,selector,seletorAll,addWork,qureyWork;
    private static final String TAG = "MainActivity";
    private CommentUtils commentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selector= (Button) this.findViewById(R.id.selector);
        addbutton = (Button) this.findViewById(R.id.add);
        seletorAll= (Button) this.findViewById(R.id.seletorAll);
        qureyWork=(Button)this.findViewById(R.id.qureyWork);
        addWork=(Button)this.findViewById(R.id.addWork);
        commentUtils = new CommentUtils(MainActivity.this);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setAddress("北京");
                student.setName("张三");
                student.setAge(23);
                commentUtils.inserStudent(student);
            }
        });

        selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = (Student) commentUtils.listOneStudent(Student.class,1);
                Log.v("student",student.getAddress());
            }
        });


        seletorAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Student> listall = commentUtils.listall(Student.class);
                Integer age = listall.get(0).getAge();
                Toast.makeText(MainActivity.this,age+"",Toast.LENGTH_LONG).show();
            }
        });

        addWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkMan workMan = new WorkMan();
                workMan.setAddress("北京");
                workMan.setName("张三");
                workMan.setAge(23);
                commentUtils.inserStudent(workMan);
            }
        });
        qureyWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkMan o = (WorkMan) commentUtils.listOneStudent(WorkMan.class, 1);
                Toast.makeText(MainActivity.this,o.getName(),Toast.LENGTH_LONG).show();
            }
        });


    }



}
