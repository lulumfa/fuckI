package fb;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeStream {

  List<Stream<Integer>> list;
  PriorityQueue<StreamComposite> queue;

  // klogk
  public MergeStream(List<Stream<Integer>> list) {
    this.list = list;
    queue = new PriorityQueue<>(new Comparator<StreamComposite>() {
      @Override
      public int compare(StreamComposite s1, StreamComposite s2) {
        return s1.value - s2.value;
      }
    });

    for (Stream<Integer> stream : list) {
      if (stream.hasNext()) {
        queue.offer(new StreamComposite(stream, stream.getNext()));
      }
    }
  }
  

  // O(1)

  public boolean hasNext() {
    return !queue.isEmpty();

  }

  // log k
  public Integer getNext() {
    StreamComposite composite = queue.poll();
    int result = composite.value;
    
    if (composite.stream.hasNext()) {
      composite.value = composite.stream.getNext();
      queue.offer(composite);
    }
    return result;
  }
}

class StreamComposite {
  Stream<Integer> stream;
  int value;
  
  public StreamComposite(Stream<Integer> stream, int value) {
    this.stream = stream;
    this.value = value;
  }
}

interface Stream <T> {
  public boolean hasNext();

  public T getNext();
}
