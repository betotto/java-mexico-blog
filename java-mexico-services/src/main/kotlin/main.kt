import io.reactivex.rxjava3.core.Flowable

fun main() {

    Flowable.just("Hello world").subscribe { println(it) }
/*

    val server = UndertowJaxrsServer()
    val serverBuilder = Undertow.builder().addHttpListener(8080, "127.0.0.1")
    server.start(serverBuilder)

    val deployment = ResteasyDeploymentImpl()
    deployment.applicationClass = JavaMexicoBlog::class.java.name
    val di = server.undertowDeployment(deployment, "/")
    di.classLoader = server::class.java.classLoader
    di.contextPath = "/"
    di.deploymentName = "Service"
    server.deploy(di)*/
}