                        🛍️ Android Kotlin E-commerce App
A full-featured E-Commerce mobile application developed in Kotlin, using MVVM, Retrofit, LiveData, and custom ViewHolders.
It allows users to browse categories, products, and manage their shopping cart, with a login system and dynamic UI/UX.

## 📋 Features Overview

- 🔐 Login system with ViewModel & Retrofit (LoginActivity + ViewModel + Repository)  
- 🏠 Home screen with product list, alternating layout (left/right)  
- 📁 Category list and filtering via CategoryListActivity  
- 📄 Product details screen  
- 🛒 Cart system with dynamic quantity & detail views  
- 🎯 QR Scan, Splash screen, Profile, Result screens  
- 🧠 MVVM structure with clean separation of concerns  
- 📡 REST API integration via RetrofitInstance.kt  
- 🧩 Custom adapters for dynamic views  

📁 Project Structure
<pre>
com.example.e_commerce/
├── Activity/
│   ├── CartActivity.kt
│   ├── CategoryListActivity.kt
│   ├── DetailActivity.kt
│   ├── MainActivity.kt
│   ├── ProfileActivity.kt
│   ├── QRScanActivity.kt
│   ├── ResultActivity.kt
│   └── SplashActivity.kt
│
├── data/
│   ├── model/
│   │   ├── LoginRequest, LoginResponse, LoggedInUser
│   │   └── ApiService, RetrofitInstance, Result.kt
│   ├── LoginRepository.kt
│   └── LoginDataSource.kt
│
├── models/
│   ├── ProductModel.kt, UserModel.kt, CartModel.kt
│   └── CategoryModel.kt, CartPostModel.kt, etc.
│
├── repository/
│   └── MainRepository.kt
│
├── ui/
│   ├── cart/CartViewModel.kt
│   ├── login/LoginActivity.kt, LoginViewModel.kt, ...
│   └── products/Adapters & MainViewModel.kt
</pre>

🚀 Getting Started
Requirements
- Android Studio (Hedgehog+)
- Android SDK 31+
- Internet access

🚀Setup
1. Clone the repo:
<pre>
git clone https://github.com/aleks2705/android-ecommerce-app.git
</pre>
2.Open in Android Studio
3.Connect device or emulator
4. Run the app: Run ▶️

✅ The app should now launch and load data dynamically!

🔐 How to Log In
The app includes a Login screen (LoginActivity.kt) that simulates user authentication. It uses a basic ViewModel + Repository pattern to validate credentials and proceed to the main screen.
You can log in using any of the users available from the FakeStoreAPI user list available here:
<pre>
  https://fakestoreapi.com/users
</pre>
Each user has a username and password field you can use.
<pre>
Example:
{
  "username": "mor_2314",
  "password": "83r5^_"
}
</pre>
Once the login is successful, the app navigates to the home screen (MainActivity.kt) and loads products, categories, and your shopping cart.
