NewsApp is simple App which uses [NewsAPI](https://newsapi.org/) to get top headlines for country you live in or you can search for a specific news.
Focus of this app is to demonstrate (Not necessarily the best practices)

- MVVM Architecture with Kotlin Flow and LiveData

# Screenshots

<img alt="NewsApp Home" height="450px" src="https://raw.githubusercontent.com/nishantpardamwar/NewsApp/master/screenshot/screen1.png" /> <img alt="NewsApp Search" height="450px" src="https://raw.githubusercontent.com/nishantpardamwar/NewsApp/master/screenshot/screen2.png" />


# Setup
You will require Android Studio 3.0 (or newer).

- Git Clone the repo
- ### Get the API Key from [NewsAPI](https://newsapi.org/)
- Find the file named `HomeDataSource` and replace `YOUR_API_KEY` with your api key
- Build app and enjoy :)

# Architecture

<img alt="NewsApp Home" height="450px" src="https://raw.githubusercontent.com/nishantpardamwar/NewsApp/master/screenshot/architecture.png" />

# Libraries and Services Used

### Libraries
- Retrofit
- Glide
- Kotlin Coroutines/Flow
- ViewModel and LiveData

### Services
- [NewsAPI](https://newsapi.org/)
- [IP Geolocation API](https://ip-api.com/)


## License
    MIT License

    Copyright (c) 2020 Nishant Pardamwar

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

