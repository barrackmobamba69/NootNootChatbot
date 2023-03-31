/**************************************************************************************************
 * Copyright (c) 2013-2019 Ashutosh Kumar Singh <ashutosh@aksingh.net>                            *
 *                                                                                                *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this           *
 * software and associated documentation files (the "Software"), to deal in the Software without  *
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,     *
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the  *
 * Software is furnished to do so, subject to the following conditions:                           *
 *                                                                                                *
 * The above copyright notice and this permission notice shall be included in all copies or       *
 * substantial portions of the Software.                                                          *
 *                                                                                                *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING  *
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND     *
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,   *
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.        *
 **************************************************************************************************/

package net.aksingh.owmjapis.api

import net.aksingh.owmjapis.model.CurrentWeather
import net.aksingh.owmjapis.model.CurrentWeatherList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherAPI {

  @GET("weather")
  fun getCurrentWeatherByCityName(
    @Query("q") name: String
  ): Call<CurrentWeather>

  @GET("weather")
  fun getCurrentWeatherByCityId(
    @Query("id") id: Int
  ): Call<CurrentWeather>

  @GET("weather")
  fun getCurrentWeatherByCoords(
    @Query("lat") lat: Double,
    @Query("lon") lon: Double
  ): Call<CurrentWeather>

  @GET("weather")
  fun getCurrentWeatherByZipCode(
    @Query("zip") zip: String
  ): Call<CurrentWeather>

  @GET("weather")
  fun getCurrentWeatherListInZone(
    @Query("bbox") box: String
  ): Call<CurrentWeatherList>

  @GET("weather")
  fun getCurrentWeatherListInCycle(
    @Query("lat") lat: Double,
    @Query("lon") lon: Double,
    @Query("cnt") count: Short
  ): Call<CurrentWeatherList>

  @GET("weather")
  fun getCurrentWeatherListByCityId(
    @Query("id") ids: String
  ): Call<CurrentWeatherList>
}
