package com.lllddd.planetsview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.cos
import kotlin.math.sin

class PlanetsView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * View宽度
     */
    private var mWidth = 0

    /**
     * View高度
     */
    private var mHeight = 0

    /**
     * 画笔
     */
    private var mPlanetPaint: Paint? = null
    private var mTrackPaint: Paint? = null

    /**
     * 太阳坐标
     */
    private var mSunX = 0f
    private var mSunY = 0f

    /**
     * 水星坐标
     */
    private var mMercuryX = 0f
    private var mMercuryY = 0f

    /**
     * 金星坐标
     */
    private var mVenusX = 0f
    private var mVenusY = 0f

    /**
     * 地球坐标
     */
    private var mEarthX = 0f
    private var mEarthY = 0f

    /**
     * 火星坐标
     */
    private var mMarsX = 0f
    private var mMarsY = 0f

    /**
     * 木星坐标
     */
    private var mJupiterX = 0f
    private var mJupiterY = 0f

    /**
     * 土星坐标
     */
    private var mSaturnX = 0f
    private var mSaturnY = 0f

    /**
     * 天王星坐标
     */
    private var mUranusX = 0f
    private var mUranusY = 0f

    /**
     * 海王星坐标
     */
    private var mNeptuneX = 0f
    private var mNeptuneY = 0f

    /**
     * 水星距离太阳的距离
     */
    private val mDistanceMercury = 90f

    /**
     * 金星距离太阳的距离
     */
    private val mDistanceVenus = 140f

    /**
     * 地球距离太阳的距离
     */
    private val mDistanceEarth = 200f

    /**
     * 火星距离太阳的距离
     */
    private val mDistanceMars = 250f

    /**
     * 木星距离太阳的距离
     */
    private val mDistanceJupiter = 310f

    /**
     * 土星距离太阳的距离
     */
    private val mDistanceSaturn = 390f

    /**
     * 天王星距离太阳的距离
     */
    private val mDistanceUranus = 440f

    /**
     * 海王星距离太阳的距离
     */
    private val mDistanceNeptune = 490f

    /**
     * 太阳角度
     */
    private var mSunAngle = 0f

    /**
     * 水星角度
     */
    private var mMercuryAngle = 0f

    /**
     * 金星角度
     */
    private var mVenusAngle = 0f

    /**
     * 地球角度
     */
    private var mEarthAngle = 0f

    /**
     * 火星角度
     */
    private var mMarsAngle = 0f

    /**
     * 木星角度
     */
    private var mJupiterAngle = 0f

    /**
     * 土星角度
     */
    private var mSaturnAngle = 0f

    /**
     * 天王星角度
     */
    private var mUranusAngle = 0f

    /**
     * 海王星角度
     */
    private var mNeptuneAngle = 0f

    /**
     * 太阳动画
     */
    private var mSunAnimator: ObjectAnimator? = null

    /**
     * 水星动画
     */
    private var mMercuryAnimator: ObjectAnimator? = null

    /**
     * 金星动画
     */
    private var mVenusAnimator: ObjectAnimator? = null

    /**
     * 地球动画
     */
    private var mEarthAnimator: ObjectAnimator? = null

    /**
     * 火星动画
     */
    private var mMarsAnimator: ObjectAnimator? = null

    /**
     * 木星动画
     */
    private var mJupiterAnimator: ObjectAnimator? = null

    /**
     * 土星动画
     */
    private var mSaturnAnimator: ObjectAnimator? = null

    /**
     * 天王星动画
     */
    private var mUranusAnimator: ObjectAnimator? = null

    /**
     * 海王星动画
     */
    private var mNeptuneAnimator: ObjectAnimator? = null

    init {
        init()
    }

    private fun init() {
        mPlanetPaint = Paint()
        mPlanetPaint!!.isAntiAlias = true
        mPlanetPaint!!.style = Paint.Style.FILL

        mTrackPaint = Paint()
        mTrackPaint!!.isAntiAlias = true
        mTrackPaint!!.style = Paint.Style.STROKE
        mTrackPaint!!.color = Color.GRAY
        mTrackPaint!!.strokeWidth = 2f

        // 设置虚线样式
        val intervals = floatArrayOf(20f, 20f)
        val phase = 2f
        val dashPathEffect = DashPathEffect(intervals, phase)
        mTrackPaint!!.pathEffect = dashPathEffect

        mSunX = width.toFloat()
        mSunY = height.toFloat()

        mMercuryX = mSunX + mDistanceMercury
        mMercuryY = mSunY

        mVenusX = mSunX + mDistanceVenus
        mVenusY = mSunY

        mEarthX = mSunX + mDistanceEarth
        mEarthY = mSunY

        mMarsX = mSunX + mDistanceMars
        mMarsY = mSunY

        mJupiterX = mSunX + mDistanceJupiter
        mJupiterY = mSunY

        mSaturnX = mSunX + mDistanceSaturn
        mSaturnY = mSunY

        mUranusX = mSunX + mDistanceUranus
        mUranusY = mSunY

        mNeptuneX = mSunX + mDistanceNeptune
        mNeptuneY = mSunY

        // 初始化属性动画
        mSunAnimator = ObjectAnimator.ofFloat(this, "sunAngle", 0f, 360f).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mMercuryAnimator = ObjectAnimator.ofFloat(this, "mercuryAngle", 0f, 360f).apply {
            duration = (4000f / 4.15f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mVenusAnimator = ObjectAnimator.ofFloat(this, "venusAngle", 0f, 360f).apply {
            duration = (4000f / 1.60f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mEarthAnimator = ObjectAnimator.ofFloat(this, "earthAngle", 0f, 360f).apply {
            duration = (4000f / 0.99f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mMarsAnimator = ObjectAnimator.ofFloat(this, "marsAngle", 0f, 360f).apply {
            duration = (4000f / 0.53f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mJupiterAnimator = ObjectAnimator.ofFloat(this, "jupiterAngle", 0f, 360f).apply {
            duration = (4000f / 0.38f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mSaturnAnimator = ObjectAnimator.ofFloat(this, "saturnAngle", 0f, 360f).apply {
            duration = (4000f / 0.13f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mUranusAnimator = ObjectAnimator.ofFloat(this, "uranusAngle", 0f, 360f).apply {
            duration = (4000f / 0.07f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }

        mNeptuneAnimator = ObjectAnimator.ofFloat(this, "neptuneAngle", 0f, 360f).apply {
            duration = (4000f / 0.05f).toLong()
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
        }
    }

    fun setSunAngle(angle: Float) {
        mSunAngle = angle
        invalidate()
    }

    fun setMercuryAngle(angle: Float) {
        mMercuryAngle = angle
        invalidate()
    }

    fun setVenusAngle(angle: Float) {
        mVenusAngle = angle
        invalidate()
    }

    fun setEarthAngle(angle: Float) {
        mEarthAngle = angle
        invalidate()
    }

    fun setMarsAngle(angle: Float) {
        mMarsAngle = angle
        invalidate()
    }

    fun setJupiterAngle(angle: Float) {
        mJupiterAngle = angle
        invalidate()
    }

    fun setSaturnAngle(angle: Float) {
        mSaturnAngle = angle
        invalidate()
    }

    fun setUranusAngle(angle: Float) {
        mUranusAngle = angle
        invalidate()
    }

    fun setNeptuneAngle(angle: Float) {
        mNeptuneAngle = angle
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        // 开始属性动画
        mSunAnimator!!.start()
        mMercuryAnimator!!.start()
        mVenusAnimator!!.start()
        mEarthAnimator!!.start()
        mMarsAnimator!!.start()
        mJupiterAnimator!!.start()
        mSaturnAnimator!!.start()
        mUranusAnimator!!.start()
        mNeptuneAnimator!!.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        // 停止属性动画
        mSunAnimator!!.cancel()
        mMercuryAnimator!!.cancel()
        mVenusAnimator!!.cancel()
        mEarthAnimator!!.cancel()
        mMarsAnimator!!.cancel()
        mJupiterAnimator!!.cancel()
        mSaturnAnimator!!.cancel()
        mUranusAnimator!!.cancel()
        mNeptuneAnimator!!.cancel()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate((mWidth shr 1).toFloat(), (mHeight shr 1).toFloat())
        canvas.drawCircle(mSunX, mSunY, mDistanceMercury, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceVenus, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceEarth, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceMars, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceJupiter, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceSaturn, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceUranus, mTrackPaint!!)
        canvas.drawCircle(mSunX, mSunY, mDistanceNeptune, mTrackPaint!!)
        canvas.restore()
        super.onDraw(canvas)
        canvas.save()
        canvas.translate((mWidth shr 1).toFloat(), (mHeight shr 1).toFloat())

        // 绘制太阳
        mPlanetPaint!!.color = Color.parseColor("#FF2200")
        canvas.drawCircle(mSunX, mSunY, SUN_RADIUS, mPlanetPaint!!)

        mMercuryX =
            (mSunX + mDistanceMercury * cos(Math.toRadians(mMercuryAngle.toDouble()))).toFloat()
        mMercuryY =
            (mSunY + mDistanceMercury * sin(Math.toRadians(mMercuryAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#6495ED")
        canvas.drawCircle(mMercuryX, mMercuryY, MERCURY_RADIUS, mPlanetPaint!!)

        mVenusX =
            (mSunX + mDistanceVenus * cos(Math.toRadians(mVenusAngle.toDouble()))).toFloat()
        mVenusY =
            (mSunY + mDistanceVenus * sin(Math.toRadians(mVenusAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#FFFF00")
        canvas.drawCircle(mVenusX, mVenusY, VENUS_RADIUS, mPlanetPaint!!)

        mEarthX =
            (mSunX + mDistanceEarth * cos(Math.toRadians(mEarthAngle.toDouble()))).toFloat()
        mEarthY =
            (mSunY + mDistanceEarth * sin(Math.toRadians(mEarthAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#00FF00")
        canvas.drawCircle(mEarthX, mEarthY, EARTH_RADIUS, mPlanetPaint!!)

        mMarsX = (mSunX + mDistanceMars * cos(Math.toRadians(mMarsAngle.toDouble()))).toFloat()
        mMarsY = (mSunY + mDistanceMars * sin(Math.toRadians(mMarsAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#D2691E")
        canvas.drawCircle(mMarsX, mMarsY, MARS_RADIUS, mPlanetPaint!!)

        mJupiterX =
            (mSunX + mDistanceJupiter * cos(Math.toRadians(mJupiterAngle.toDouble()))).toFloat()
        mJupiterY =
            (mSunY + mDistanceJupiter * sin(Math.toRadians(mJupiterAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#FFE4B5")
        canvas.drawCircle(mJupiterX, mJupiterY, JUPITER_RADIUS, mPlanetPaint!!)

        mSaturnX =
            (mSunX + mDistanceSaturn * cos(Math.toRadians(mSaturnAngle.toDouble()))).toFloat()
        mSaturnY =
            (mSunY + mDistanceSaturn * sin(Math.toRadians(mSaturnAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#A0522D")
        canvas.drawCircle(mSaturnX, mSaturnY, SATURN_RADIUS, mPlanetPaint!!)

        mUranusX =
            (mSunX + mDistanceUranus * cos(Math.toRadians(mUranusAngle.toDouble()))).toFloat()
        mUranusY =
            (mSunY + mDistanceUranus * sin(Math.toRadians(mUranusAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#008080")
        canvas.drawCircle(mUranusX, mUranusY, URANUS_RADIUS, mPlanetPaint!!)

        mNeptuneX =
            (mSunX + mDistanceNeptune * cos(Math.toRadians(mNeptuneAngle.toDouble()))).toFloat()
        mNeptuneY =
            (mSunY + mDistanceNeptune * sin(Math.toRadians(mNeptuneAngle.toDouble()))).toFloat()
        mPlanetPaint!!.color = Color.parseColor("#4B0082")

        canvas.drawCircle(mNeptuneX, mNeptuneY, NEPTUNE_RADIUS, mPlanetPaint!!)

        canvas.restore()

        invalidate()
    }

    companion object {
        /**
         * 地球半径
         */
        private const val SUN_RADIUS = 50.0f

        /**
         * 地球半径
         */
        private const val EARTH_RADIUS = 20.0f

        /**
         * 水星半径
         */
        private const val MERCURY_RADIUS = EARTH_RADIUS * 0.7f

        /**
         * 金星半径
         */
        private const val VENUS_RADIUS = EARTH_RADIUS * 1.2f

        /**
         * 火星半径
         */
        private const val MARS_RADIUS = EARTH_RADIUS * 0.9f

        /**
         * 木星半径
         */
        private const val JUPITER_RADIUS = EARTH_RADIUS * 2f

        /**
         * 土星半径
         */
        private const val SATURN_RADIUS = EARTH_RADIUS * 1.7f

        /**
         * 天王星半径
         */
        private const val URANUS_RADIUS = EARTH_RADIUS * 1.2f

        /**
         * 海王星半径
         */
        private const val NEPTUNE_RADIUS = EARTH_RADIUS * 1.2f
    }
}