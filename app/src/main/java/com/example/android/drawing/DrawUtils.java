package com.example.android.drawing;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.Random;

public abstract class DrawUtils {

    public static void drawCourse1(Canvas canvas) {
        Paint p = new Paint();
        Rect rect = new Rect();
        RectF rectf = new RectF(700, 100, 800, 150);
        float[] points = new float[]{100, 50, 150, 100, 150, 200, 50, 200, 50, 100};
        float[] points1 = new float[]{300, 200, 600, 200, 300, 300, 600, 300, 400, 100, 400, 400, 500, 100, 500, 400};

        rectf = new RectF(700, 100, 800, 150);

        Random random = new Random();

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int color = Color.rgb(r, g, b);

        // заливка цветом
        canvas.drawColor(Color.WHITE);

        // настройка кисти
        // красный цвет
        p.setColor(color);
        // толщина линии = 10
        p.setStrokeWidth(10);

        // рисуем точку (50,50)
        canvas.drawPoint(50, 50, p);

        // рисуем линию от (100,100) до (500,50)
        canvas.drawLine(100, 100, 500, 50, p);

        // рисуем круг с центром в (100,200), радиус = 50
        canvas.drawCircle(100, 200, 50, p);

        // рисуем прямоугольник
        // левая верхняя точка (200,150), нижняя правая (400,200)
        canvas.drawRect(200, 150, 400, 200, p);

        // настройка объекта Rect
        // левая верхняя точка (250,300), нижняя правая (350,500)
        rect.set(250, 300, 350, 500);
        // рисуем прямоугольник из объекта rect
        canvas.drawRect(rect, p);

        // рисуем точки из массива points
        canvas.drawPoints(points, p);

        // рисуем линии по точкам из массива points1
        canvas.drawLines(points1, p);

        // перенастраиваем кисть на зеленый цвет
        p.setColor(Color.GREEN);

        // рисуем закругленный прямоугольник по координатам из rectf
        // радиусы закругления = 20
        canvas.drawRoundRect(rectf, 20, 20, p);

        // смещаем коорднаты rectf на 150 вниз
        rectf.offset(0, 150);
        // рисуем овал внутри прямоугольника rectf
        canvas.drawOval(rectf, p);

        // смещаем rectf в (900,100) (левая верхняя точка)
        rectf.offsetTo(900, 100);
        // увеличиваем rectf по вертикали на 25 вниз и вверх
        // (a,b) -> (a+0, b-25)
        rectf.inset(0, -25);
        // рисуем дугу внутри прямоугольника rectf
        // с началом в 90, и длиной 270 градусов (от начала, а не вообще до 270)
        // отсчёт ведётся по часовой стрелке от 3-ёх часов (0) по часовой
        // соединение крайних точек через центр
        canvas.drawArc(rectf, 90, 270, true, p);

        // смещаем коорднаты rectf на 150 вниз
        rectf.offset(0, 150);
        // рисуем дугу внутри прямоугольника rectf
        // с началом в 90, и длиной 270
        // соединение крайних точек напрямую
        canvas.drawArc(rectf, 90, 270, false, p);

        // перенастраиваем кисть на толщину линии = 3
        p.setStrokeWidth(3);
        // рисуем линию (150,450) - (150,600)
        canvas.drawLine(150, 450, 150, 600, p);

        // перенастраиваем кисть на синий цвет
        p.setColor(Color.BLUE);

        // настраиваем размер текста = 30
        p.setTextSize(30);
        p.setTextAlign(Paint.Align.LEFT);
        // рисуем текст в точке (150,500) (
        canvas.drawText("text left", 150, 500, p);

        // настраиваем выравнивание текста на центр
        p.setTextAlign(Paint.Align.CENTER);
        // рисуем текст в точке (150,525)
        canvas.drawText("text center", 150, 525, p);

        // настраиваем выравнивание текста на левое
        p.setTextAlign(Paint.Align.RIGHT);
        // рисуем текст в точке (150,550)
        canvas.drawText("text right", 150, 550, p);
    }

    /**
     * http://startandroid.ru/ru/uroki/vse-uroki-spiskom/312-urok-142-risovanie-prostye-figury-tekst.html
     */
    public static void drawCourse2(Canvas canvas) {
        Paint p = new Paint();
        Rect rect = new Rect(100, 200, 200, 300);
        StringBuilder sb = new StringBuilder();

        canvas.drawARGB(80, 102, 204, 255);

        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);

        p.setTextSize(30);

        // создаем строку с значениями ширины и высоты канвы
        sb.setLength(0);
        sb.append("width = ").append(canvas.getWidth())
                .append(", height = ").append(canvas.getHeight());
        canvas.drawText(sb.toString(), 100, 100, p);

        // перенастраивам кисть на заливку
        // Achtung! По умолчанию используется стиль Paint.Style.FILL. !!!
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, p);

        // перенастраивам кисть на контуры
        p.setStyle(Paint.Style.STROKE);
        rect.offset(150, 0);
        canvas.drawRect(rect, p);

        // перенастраивам кисть на заливку + контуры
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        rect.offset(150, 0);
        canvas.drawRect(rect, p);

    }

    /** Рисование - Path.
     * http://startandroid.ru/ru/uroki/vse-uroki-spiskom/316-urok-143-risovanie-path.html
     */
    public static void drawCourse3(Canvas canvas) {
        Paint p = new Paint();
        p.setStrokeWidth(3);
        p.setStyle(Paint.Style.STROKE);

        RectF rectf = new RectF(350,100,400,150);
        Path path = new Path();
        Path path1 = new Path();

        canvas.drawARGB(80, 102, 204, 255);

        // очистка path
        path.reset();

        // угол
        // ставит «курсор» в указанную точку
        path.moveTo(100, 100);
        //  рисует линию от текущей точки до указанной, следующее рисование пойдет уже от указанной точки
        path.lineTo(150, 200);
        path.lineTo(50, 200);

        // треугольник
        path.moveTo(250, 100);
        path.lineTo(300, 200);
        path.lineTo(200, 200);
        // закрываем текущий контур (замыкаем)
        path.close();

        // квадрат и круг
        path.addRect(rectf, Path.Direction.CW);
        path.addCircle(450, 150, 25, Path.Direction.CW);

        // рисование path
        p.setColor(Color.BLACK);
        canvas.drawPath(path, p);

        // очистка path1
        path1.reset();

        // две пересекающиеся линии
        path1.moveTo(50,50);
        path1.lineTo(500,250);
        path1.moveTo(500,50);
        path1.lineTo(50,250);

        // рисование path1
        p.setColor(Color.GREEN);
        canvas.drawPath(path1, p);


        // добавление path1 к path
        path.addPath(path1);

        // смещение
        path.offset(500,100);

        // рисование path
        p.setColor(Color.BLUE);
        canvas.drawPath(path, p);

        /* Методы moveTo, lineTo, quadTo, cubicTo имеют одноименные аналоги, но начинающиеся
        с буквы r: rMoveTo, rLineTo, rQuadTo, rCubicTo. Отличие r-методов в том, что они используют
        не абсолютные, а относительные (relative – отсюда и буква r) координаты. */

        //*** Вторая Часть - кривые линии***//
        // точки искривления
        Point point1 = new Point(200,300);
        Point point21 = new Point(500,600);
        Point point22 = new Point(900,200);
        // настраиваем сглаживание
        p = new Paint(Paint.ANTI_ALIAS_FLAG);

        // первая линия
        p.setColor(Color.BLACK);
        canvas.drawLine(100, 100, 600, 100, p);

        // точка отклонения для первой линии
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GREEN);
        canvas.drawCircle(point1.x, point1.y, 10, p);

        // квадратичная кривая
        path.reset();
        path.moveTo(100, 100);
        path.quadTo(point1.x, point1.y, 600, 100);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, p);


        // вторая линия
        p.setColor(Color.BLACK);
        canvas.drawLine(400, 400, 1100, 400, p);

        // точки отклонения для второй линии
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
        canvas.drawCircle(point21.x, point21.y, 10, p);
        canvas.drawCircle(point22.x, point22.y, 10, p);

        // кубическая кривая
        path.reset();
        path.moveTo(400, 400);
        path.cubicTo(point21.x, point21.y, point22.x, point22.y, 1100, 400);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, p);


        //*** Третья часть ***//
        p.setStrokeWidth(1);
        p.setTextSize(20);
        path = new Path();
        String text = "Draw the text, with origin at (x,y), using the specified paint";

        // черный
        path.reset();
        path.addCircle(200, 200, 100, Path.Direction.CW);
        p.setColor(Color.BLACK);
        canvas.drawTextOnPath(text, path, 0, 0, p);

        path.reset();
        path.addCircle(500, 200, 100, Path.Direction.CCW);

        // синий
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
        canvas.drawTextOnPath(text, path, 0, 0, p);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, p);

        // зеленый
        path.offset(-300, 250);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GREEN);
        canvas.drawTextOnPath(text, path, 100, 0, p);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, p);

        // красный
        path.offset(300, 0);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        canvas.drawTextOnPath(text, path, 0, 30, p);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, p);
    }

    public static void drawCourseTemplate(Canvas canvas) {
        Paint p = new Paint();
        p.setStrokeWidth(3);
        p.setStyle(Paint.Style.STROKE);

        int r = 255;
        int g = 0;
        int b = 0;
        int color = Color.rgb(r, g, b);

    }
}
