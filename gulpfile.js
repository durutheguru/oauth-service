var gulp = require('gulp');
var cssnano = require('gulp-cssnano');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');

var gulp_helper = require("./internal/gulp_helper");



/**
 * CSS task
 */

var VENDOR_CSS_ROOT = "./src/main/resources/static/vendor";

var cssVendorList = [
    "/bootstrap/css/bootstrap.min.css",
    "/bootstrap-icons/bootstrap-icons.css",
    "/boxicons/css/boxicons.min.css",
    "/quill/quill.snow.css",
    "/quill/quill.bubble.css",
    "/remixicon/remixicon.css",
    "/simple-datatables/style.css"
];

gulp.task('vendorCSSMin', function() {

    return gulp.src(gulp_helper.mergePathRoot(VENDOR_CSS_ROOT, cssVendorList))
        .pipe(concat("compiled_vendor.css"))
        .pipe(cssnano())
        .pipe(gulp.dest("./src/main/resources/static/compiled"));

});

/**
 * End CSS Task
 * #############################################
 */



/**
 * SASS task
 */
gulp.task('sassCompile', function () {
    return gulp.src('./src/main/resources/static/app/sass/**/*.scss')
        .pipe(sass())
        .pipe(concat("app.css"))
        .pipe(cssnano())
        .pipe(gulp.dest('./src/main/resources/static/compiled'));
});

/**
 * End SASS Task
 * #############################################
 */


 /**
  * Final CSS Compile task
  */
 gulp.task('finalCSSCompile', function () {
     return gulp.src([
        './src/main/resources/static/compiled/compiled_vendor.css',
        './src/main/resources/static/theme/css/style.css',
        './src/main/resources/static/compiled/app.css'
        ])
         .pipe(concat("compiled.css"))
         .pipe(cssnano())
         .pipe(gulp.dest('./src/main/resources/static/compiled'));
 });

 /**
  * End Final CSS Compile task
  * #############################################
  */



/**
 * Javascript task
 */

var VENDOR_JS_ROOT = "./src/main/resources/static/vendor";

var vendorJSList = gulp_helper.mergePathRoot(VENDOR_JS_ROOT, [
    "/apexcharts/apexcharts.min.js",
    "/bootstrap/js/bootstrap.bundle.min.js",
    "/chart.js/chart.min.js",
    "/echarts/echarts.min.js",
    "/quill/quill.min.js",
    "/simple-datatables/simple-datatables.js",
    "/tinymce/tinymce.min.js",
    "/php-email-form/validate.js"
]);


gulp.task('vendorJSMin', function() {

    return gulp.src(vendorJSList)
        .pipe(concat("compiled_vendor.js"))
        .pipe(uglify())
        .pipe(gulp.dest("./src/main/resources/static/compiled"));

});


/**
 * End Javascript task
 * #############################################
 */


/**
 * JS App Compile task
 */
gulp.task('jsAppCompile', function () {
    return gulp.src('./src/main/resources/static/app/js/**/*.js')
        .pipe(concat("app.js"))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/resources/static/compiled'));
});

/**
 * End JS App Compile task
 * #############################################
 */



/**
  * Final JS Compile task
  */
 gulp.task('finalJSCompile', function () {
     return gulp.src([
        './src/main/resources/static/compiled/compiled_vendor.js',
        './src/main/resources/static/theme/js/main.js',
        './src/main/resources/static/compiled/app.js'
        ])
         .pipe(concat("compiled.js"))
         .pipe(gulp.dest('./src/main/resources/static/compiled'));
 });

 /**
  * End Final JS Compile task
  * #############################################
  */




/**
 * Default task
 */

gulp.task(
    "default",
    gulp.series(
        "vendorCSSMin",
        "sassCompile",
        "finalCSSCompile",
        "vendorJSMin",
        "jsAppCompile",
        "finalJSCompile",
    )
);

/**
 * End Default task
 * #############################################
 */
