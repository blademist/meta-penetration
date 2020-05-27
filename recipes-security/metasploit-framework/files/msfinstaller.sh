#!/bin/bash

msfname="metasploit-framework"
msfver="5.0.90"
msfpkg="${msfver}.tar.gz"
msfurl="https://github.com/rapid7/metasploit-framework/archive/"

basepath="/opt/"
msfpath="${basepath}${msfname}"

mycurl=`which curl`
if [ ! -z ${mycurl} ]; then
	download_cmd="${mycurl} -R -L -f --retry 3 --retry-delay 4 --connect-timeout 180 --compressed -C - -o"
else
	echo "Can not find curl command, please install it first."
	exit 1
fi

if [ ! -d ${basepath} ]; then
	mkdir -p ${basepath}
fi

if [ -d ${msfpath} ]; then
	echo "Clean up ${msfpath} ..."
	rm -rf ${msfpath}
fi

echo "Downloading metasploit-framework source tarball ..."
cd ${basepath}
${download_cmd} ${msfpkg}".#part" ${msfurl}${msfpkg}
if [ $? -eq 0 ]; then
	mv -f ${msfpkg}".#part" ${msfpkg}
	echo "Download ${msfpkg} successfully."
else
	echo "Download ${msfpkg} failed."
	exit 1
fi

echo "Extracting metasploit-framework source ..."
tar xzf ${basepath}/${msfpkg} -C ${basepath}
mv ${basepath}/${msfname}-${msfver} ${msfpath}
echo "Done"

echo "Installing metasploit-framework ..."
cd ${msfpath}

# Fix bundle verion checking
sed -i "/^BUNDLED.*/,+1d" ${msfpath}/Gemfile.lock

# Configure RubyGems Mirror
bundle config mirror.https://rubygems.org https://gems.ruby-china.com

bundle config build.nokogiri --use-system-libraries
bundle install
if [ $? -ne 0 ]; then
	echo "Failed to install metasploit-framework."
	exit 1
else
	echo "Install metasploit-framework successfully."
fi

echo "Creating symbolic links for msf tools ..."
if [ -e /usr//bin/msfconsole ]; then
	rm /usr//bin/msfconsole
fi
ln -sf ${msfpath}/msfconsole /usr/bin/

if [ -e /usr/bin/msfvenom ]; then
	rm /usr//bin/msfvenom
fi
ln -sf ${msfpath}/msfvenom /usr/bin/
echo "Done"

echo "Clean up ${msfpkg} ..."
rm -rf ${basepath}/${msfpkg}
echo "Done"

exit 0
