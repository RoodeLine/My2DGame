.PHONY: setup check-deps install-tools install-jdk install-javafx compile run clean

JDK_VERSION = 17
JFX_VERSION = 17.0.2
JFX_HOME = lib/javafx-sdk-$(JFX_VERSION)
JFX_URL = https://download2.gluonhq.com/openjfx/17.0.2/openjfx-17.0.2_linux-x64_bin-sdk.zip
JFX_MODULES = javafx.controls,javafx.fxml,javafx.graphics

JAVA_FILES = $(shell find src/main/java -name "*.java")
MAIN_CLASS = com.mygame.Main

check-deps:
	@echo "Checking system requirements..."
	@if ! which make >/dev/null; then \
		echo "Error: make is required. Install with: sudo apt install make"; \
		exit 1; \
	fi
	@if ! which curl >/dev/null; then \
		echo "Error: curl is required. Install with: sudo apt install curl"; \
		exit 1; \
	fi
	@if ! which unzip >/dev/null; then \
		echo "Error: unzip is required. Install with: sudo apt install unzip"; \
		exit 1; \
	fi
	@echo "All basic dependencies are available"

install-tools:
	@echo "Installing essential build tools (make, curl, unzip)..."
	@sudo apt-get update -qq
	@sudo apt-get install -y make curl unzip || (echo "Failed to install essential tools"; exit 1)
	@echo "Essential tools installed"

install-jdk:
	@echo "Checking JDK installation..."
	@if ! which javac >/dev/null; then \
		echo "Installing OpenJDK $(JDK_VERSION)..."; \
		sudo apt-get install -y openjdk-$(JDK_VERSION)-jdk || (echo "Failed to install JDK"; exit 1); \
		echo "OpenJDK $(JDK_VERSION) installed"; \
	else \
		echo "JDK already installed"; \
	fi

install-javafx:
	@if [ ! -d "$(JFX_HOME)/lib" ]; then \
		echo "Downloading JavaFX $(JFX_VERSION) SDK..."; \
		mkdir -p lib; \
		curl -sSL $(JFX_URL) -o lib/javafx.zip || (echo "Download failed"; exit 1); \
		unzip -q lib/javafx.zip -d lib || (echo "Extraction failed"; exit 1); \
		mv lib/javafx-sdk-$(JFX_VERSION) $(JFX_HOME) || (echo "Move failed"; exit 1); \
		rm lib/javafx.zip; \
		echo "JavaFX $(JFX_VERSION) installed to $(JFX_HOME)"; \
	else \
		echo "JavaFX SDK already installed"; \
	fi

setup: install-tools install-jdk install-javafx
	@echo "Environment setup complete"

compile:
	@mkdir -p bin
	@echo "Compiling project..."
	@javac --release 17 --module-path $(JFX_HOME)/lib --add-modules $(JFX_MODULES) \
		-d bin $(JAVA_FILES) || (echo "Compilation failed"; exit 1)

run:
	@echo "Starting application..."
	@java --module-path $(JFX_HOME)/lib --add-modules $(JFX_MODULES) \
		-cp bin $(MAIN_CLASS)

clean:
	@echo "Cleaning build artifacts..."
	@rm -rf bin lib


default: setup compile run

.DEFAULT_GOAL := default
