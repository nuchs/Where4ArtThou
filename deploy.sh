# You need to update the following files first
#
# Add the following to your bashrc
# export HADOOP_HOME=$HOME/hadoop
# export PATH=$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH
#
# etc/hadoop/core-site.xml:
#<configuration>
#   <property>
#       <name>fs.defaultFS</name>
#       <value>hdfs://localhost:9000</value>
#   </property>
#</configuration>
#
#etc/hadoop/hdfs-site.xml:
#<configuration>
#   <property>
#       <name>dfs.replication</name>
#       <value>1</value>
#   </property>
#</configuration>
#
# etc/hadoop/hadoop-env.sh
# JAVA_HOME should point to your java dist
# export JAVA_HOME="/usr/lib/jvm/java-7-openjdk-i386/"
# export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
# export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib"
# export HADOOP_DATANODE_OPTS="-client -Dhadoop.security.logger=ERROR,RFAS $HADOOP_DATANODE_OPTS"

# Create the ssh keys to allow passwordless acceess
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

# Setup the hadoop distributed filesystem and add the program files
hdfs namenode -format
start-dfs.sh
hdfs dfs -mkdir /w4at
hdfs dfs -mkdir /w4at/libs
hdfs dfs -mkdir /w4at/input
hdfs dfs -put input/* /w4at/input/
hdfs dfs -put gson-2.3.jar /w4at/libs/
