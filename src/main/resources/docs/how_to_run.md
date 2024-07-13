## How to run?

<br/>


### Prerequisites

The following apps should be installed before running the application:

- A command line app
- Docker Desktop
  <br/>

> [!NOTE]
> For more information regarding the system requirements, etc. refer to the following pages: <br/>
> [Install on Mac](https://docs.docker.com/desktop/install/mac-install/)<br/>
> [Install on Windows](https://docs.docker.com/desktop/install/windows-install/)<br/>
> [Install on Linux](https://docs.docker.com/desktop/install/linux-install/)<br/>

<br/>


### Running the application

In order to run the application, apply the following steps:

1. Run Docker desktop.

<br/>

2. Open command prompt window and clone the project from GitHub using the following command:

```shell
git clone https://github.com/yildizmy/bank-account-api.git
```
<br/>

3. Change the current directory to the project directory where the `docker-compose.yml` file is located:

```shell
cd bank-account-api
```
<br/>

4. Run the following command to compose and start up database container of the application on Docker.

```shell
docker compose up --build
```
<br/>

5. After the `axonserver` container starts on Docker, open the project using `IntelliJ IDEA` or another IDE. Then select `Java 21` version via `File > Project Structure > Project > SDK` menu and run the application.

> [!IMPORTANT]
> If _Lombok requires enabled annotation processing_ dialog appears at this stage, click _Enable annotation processing_ button.

<br/>

At this step, the application starts on http://localhost:8080/ and can be tested by using Postman, etc. For this purpose, see the details on [How to test?](how_to_test.md) section.

<br/>


### Documentation

[docker compose up](https://docs.docker.com/engine/reference/commandline/compose_up/)<br/>

<br/>
<br/>
