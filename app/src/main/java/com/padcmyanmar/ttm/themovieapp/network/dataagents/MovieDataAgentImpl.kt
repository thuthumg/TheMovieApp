//package com.padcmyanmar.ttm.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.google.gson.Gson
//import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
//import com.padcmyanmar.ttm.themovieapp.network.responses.MovieListResponse
//import com.padcmyanmar.ttm.themovieapp.utils.API_GET_NOW_PLAYING
//import com.padcmyanmar.ttm.themovieapp.utils.BASE_URL
//import com.padcmyanmar.ttm.themovieapp.utils.MOVIE_API_KEY
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.lang.Exception
//import java.lang.StringBuilder
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl : MovieDataAgent {
////    override fun getNowPlayingMovies() {
////       GetNowPlayingMovieTask().execute()
////    }
//
//    class GetNowPlayingMovieTask() : AsyncTask<Void,Void, MovieListResponse>(){
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//           val url:URL
//
//           var reader: BufferedReader?= null
//            val stringBuilder:StringBuilder
//
//            try{
//                //create the HttpURLConnection
//                url = URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")//1.
//                val connection = url.openConnection() as HttpURLConnection //2.
//
//                //Set HTTP Method
//                connection.requestMethod = "GET" //3.
//
//                //give it 15 seconds to response
//                connection.readTimeout = 15 * 1000 //4. ms
//
//                connection.doInput = true //5.
//                connection.doOutput = false //6.
//
//                connection.connect() // 7.
//
//                //read the output form the server
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                )//8.
//
//                stringBuilder = StringBuilder()
//
//                for(line in reader.readLines()){
//                    stringBuilder.append(line + "\n")
//                }
//
//                val responseString = stringBuilder.toString()
//                Log.d("NowPlayingMovies",responseString)
//
//                val movieListResponse = Gson().fromJson(
//                    responseString,
//                    MovieListResponse::class.java
//                ) //9.
//
//                return movieListResponse
//
//            }catch (e:Exception){
//                e.printStackTrace()
//                Log.e("NewsError",e.message?: "")
//            }finally {
//                if(reader != null){
//                    try {
//                        reader.close()
//                    }catch (ioe:IOException){
//                        ioe.printStackTrace()
//                    }
//                }
//
//            }
//
//             return null
//
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            println(result.toString())
//            super.onPostExecute(result)
//        }
//
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        //TODO("Not yet implemented")
//    }
//}