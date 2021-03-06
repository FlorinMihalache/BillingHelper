# BillingHelper
Wrapper around Google Play Billing Library, simplifying its use. 
Handles client connection, querying sku details, owned purchase, different purchase types, acknowledging purchases etc.

## How does it work?
Make sure your Activity/Fragment implements BillingListener and initializes BillingHelper
``` kotlin
class MainActivity: AppCompatActivity(), BillingListener {

    lateinit var billing: BillingHelper

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // construct helper, throw in context, supported skus (for query puproses)
        // and a callback listener
        billing = BillingHelper(
                context = this, 
                skuNames = listOf("inAppPurchaseSkuName1", "inAppPurchaseSkuName2", "subscriptionSkuName")
                billingListener = this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // make sure to clean it up when you're done
        billing.endBillingClientConnection()
    }

    override fun onBillingEvent(event: BillingEvent, message: String?) {
        // receive an event based on calls to billing
    }
}
```

<br/>Use any of its public methods, BillingHelper will do all the heavy lifting and always report changes via BillingListener
``` kotlin
fun consumePurchase(purchase: Purchase)

fun endBillingClientConnection()

fun getSkuDetails(skuName: String): SkuDetails?

fun isBillingReady(): Boolean

fun isPurchased(skuName: String): Boolean

fun launchPurchaseFlow(activity: Activity, skuName: String)

fun launchPriceChangeConfirmationFlow(activity: Activity, skuDetails: SkuDetails)

fun getPurchaseForSkuName(skuName: String): Purchase?

fun initQueryOwnedPurchases() // by default handled automatically 

fun initQuerySkuDetails() // by default handled automatically 

fun addBillingListener(@UiThread listener: BillingListener)

fun removeBillingListener(listener: BillingListener)
```

<br/>BillingEvent includes all of the things you might be interested in, served via BillingListener 
``` kotlin
enum class BillingEvent {
    BILLING_CONNECTED,
    BILLING_CONNECTION_FAILED,
    BILLING_DISCONNECTED,
    QUERY_SKU_DETAILS_COMPLETE,
    QUERY_SKU_DETAILS_FAILED,
    QUERY_OWNED_PURCHASES_COMPLETE,
    QUERY_OWNED_PURCHASES_FAILED,
    PURCHASE_COMPLETE,
    PURCHASE_FAILED,
    PURCHASE_CANCELLED,
    PURCHASE_ACKNOWLEDGED,
    PURCHASE_ACKNOWLEDGE_FAILED,
    CONSUME_PURCHASE_SUCCESS,
    CONSUME_PURCHASE_FAILED,
    PRICE_CHANGE_CONFIRMATION_SUCCESS,
    PRICE_CHANGE_CONFIRMATION_CANCELLED,
}
```

## Great! How do I get started?
Make sure root build.gradle repositories include JitPack
``` gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

And BillingHelper dependency is added to app build.gradle
``` gradle
dependencies {
    implementation "com.github.mvojtkovszky:BillingHelper:$latest_version"
}
```
